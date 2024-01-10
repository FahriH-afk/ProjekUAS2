package com.example.projekuas.ui.theme.EditBarang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekuas.database.BarangRepositori
import com.example.projekuas.ui.theme.AddUIState
import com.example.projekuas.ui.theme.DetailBarangSewa
import com.example.projekuas.ui.theme.DetailPelanggan
import com.example.projekuas.ui.theme.EditPelanggan.EditDestination
import com.example.projekuas.ui.theme.toBarang
import com.example.projekuas.ui.theme.toPelanggan
import com.example.projekuas.ui.theme.toUIStateBarangSewa
import com.example.projekuas.ui.theme.toUIStatePelanggan
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class EditBarangBiewModel (
    savedStateHandle: SavedStateHandle,
    private val repositori: BarangRepositori
) : ViewModel () {

    var sewaaUIState by mutableStateOf(AddUIState())
        private set

    private val sewaId: String = checkNotNull(savedStateHandle[EditBarangDestionation.sewaId])

    init {
        viewModelScope.launch {
            sewaaUIState =
                repositori.getBarangById(sewaId)
                    .filterNotNull()
                    .first()
                    .toUIStateBarangSewa()
        }
    }

    fun updateUIState(addEvent: DetailBarangSewa) {
        sewaaUIState = sewaaUIState.copy(detailBarangSewa = addEvent)
    }

    suspend fun updateBarangSewa() {
        repositori.update(sewaaUIState.detailBarangSewa.toBarang())
    }
}