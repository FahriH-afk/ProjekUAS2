package com.example.projekuas.ui.theme.AddPelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projekuas.database.SewaRepository
import com.example.projekuas.ui.theme.AddUIState
import com.example.projekuas.ui.theme.DetailPelanggan
import com.example.projekuas.ui.theme.toPelanggan

class PelangganViewModel(private val sewaRepository: SewaRepository): ViewModel() {
    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(detailPelanggan: DetailPelanggan) {
        addUIState = AddUIState(detailPelanggan = detailPelanggan)
    }

    suspend fun addPelanggan() {
        sewaRepository.save(addUIState.detailPelanggan.toPelanggan())
    }
}