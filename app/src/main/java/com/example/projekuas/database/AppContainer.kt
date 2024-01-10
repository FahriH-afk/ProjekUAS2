package com.example.projekuas.database

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val sewaRepository: SewaRepository

    val barangRepositori: BarangRepositori
}
class SewaContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val sewaRepository: SewaRepository by lazy {
        PelangganRepositoryImpl(firestore)
    }

    override val barangRepositori: BarangRepositori by lazy {
        BarangRepositoriImpl(firestore)
    }
}
