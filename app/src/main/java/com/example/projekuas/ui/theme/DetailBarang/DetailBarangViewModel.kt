package com.example.projekuas.ui.theme.DetailBarang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekuas.database.BarangRepositori
import com.example.projekuas.ui.theme.DetailBarangSewa
import com.example.projekuas.ui.theme.DetailPelangga.DetailDestination
import com.example.projekuas.ui.theme.DetailUIState
import com.example.projekuas.ui.theme.DetailViewMoedlPelanggan.DetailPelangganViewModel
import com.example.projekuas.ui.theme.toDetailBarangSewa
import com.example.projekuas.ui.theme.toDetailPelanggan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailBarangViewModel (
    savedStateHandle: SavedStateHandle,
    private val  repositori: BarangRepositori
) : ViewModel () {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val sewaId: String = checkNotNull(savedStateHandle[DetailBarangDestination.IdSewa])

    val uiState: StateFlow<DetailUIState> =
        repositori.getBarangById(sewaId)
            .filterNotNull()
            .map {
                DetailUIState(detailBarangSewa = it.toDetailBarangSewa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteBarangSewa() {
        repositori.delete(sewaId)

    }

}