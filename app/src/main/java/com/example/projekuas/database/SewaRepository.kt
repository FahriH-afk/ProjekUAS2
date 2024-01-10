package com.example.projekuas.database

import android.content.ContentValues
import android.util.Log
import com.example.projekuas.Model.Pelanggan
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface SewaRepository {
    fun getAll(): Flow<List<Pelanggan>>
    suspend fun save(pelanggan: Pelanggan): String
    suspend fun update(pelanggan: Pelanggan)
    suspend fun delete(pelangganId: String)
    fun getPelangganById(kontakId: String): Flow<Pelanggan>
}
class PelangganRepositoryImpl(private val firestore: FirebaseFirestore) : SewaRepository {
    override fun getAll(): Flow<List<Pelanggan>> = flow {
        val snapshot = firestore.collection("Pelanggan")
            .orderBy("namaPelanggan", Query.Direction.ASCENDING)
            .get()
            .await()
        val pelanggan = snapshot.toObjects(Pelanggan::class.java)
        emit(pelanggan)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(pelanggan: Pelanggan): String {
        return try {
            val documentReference = firestore.collection("Pelanggan").add(pelanggan).await()
            // Update the Kontak with the Firestore-generated DocumentReference
            firestore.collection("Pelanggan").document(documentReference.id)
                .set(pelanggan.copy(idPelanggan = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(pelanggan: Pelanggan) {
        firestore.collection("Pelanggan").document(pelanggan.idPelanggan).set(pelanggan).await()
    }

    override suspend fun delete(pelangganId: String) {
        firestore.collection("Pelanggan").document(pelangganId).delete().await()
    }

    override fun getPelangganById(pelangganId: String): Flow<Pelanggan> {
        return flow {
            val snapshot = firestore.collection("Pelanggan").document(pelangganId).get().await()
            val pelanggan = snapshot.toObject(Pelanggan::class.java)
            emit(pelanggan!!)
        }.flowOn(Dispatchers.IO)
    }
}