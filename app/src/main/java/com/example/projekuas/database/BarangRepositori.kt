package com.example.projekuas.database

import android.content.ContentValues
import android.util.Log
import com.example.projekuas.Model.BarangSewa
import com.example.projekuas.Model.Pelanggan
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface BarangRepositori {
    fun getAll(): Flow<List<BarangSewa>>
    suspend fun save(barangSewa: BarangSewa): String
    suspend fun update(barangSewa: BarangSewa)
    suspend fun delete(barangSewaId: String)
    fun getBarangById(barangSewaId: String): Flow<BarangSewa>
}

class BarangRepositoriImpl(private val firestore: FirebaseFirestore) : BarangRepositori {
    override fun getAll(): Flow<List<BarangSewa>> = flow {
        val snapshot = firestore.collection("BarangSewa")
            .orderBy("BarangSewa", Query.Direction.ASCENDING)
            .get()
            .await()
        val barangSewa = snapshot.toObjects(BarangSewa::class.java)
        emit(barangSewa)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(barangSewa: BarangSewa): String {
        return try {
            val documentReference = firestore.collection("BarangSewa").add(barangSewa).await()
            // Update the Kontak with the Firestore-generated DocumentReference
            firestore.collection("Pelanggan").document(documentReference.id)
                .set(barangSewa.copy(idSewa = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(barangSewa: BarangSewa) {
        firestore.collection("Barang Sewa").document(barangSewa.idSewa).set(barangSewa).await()
    }

    override suspend fun delete(sewaId: String) {
        firestore.collection("Barang Sewa").document(sewaId).delete().await()
    }

    override fun getBarangById(sewaId: String): Flow<BarangSewa> {
        return flow {
            val snapshot = firestore.collection("BarangSewa").document(sewaId).get().await()
            val barangSewa = snapshot.toObject(BarangSewa::class.java)
            emit(barangSewa!!)
        }.flowOn(Dispatchers.IO)
    }
}