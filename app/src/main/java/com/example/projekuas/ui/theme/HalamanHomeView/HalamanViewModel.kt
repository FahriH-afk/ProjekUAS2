package com.example.projekuas.ui.theme.HalamanHomeView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekuas.Model.Pelanggan
import com.example.projekuas.database.SewaRepository
import com.example.projekuas.ui.theme.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


sealed class PelangganUIState {
    data class Success(val pelanggan: Flow<List<Pelanggan>>) : PelangganUIState()
    object Error : PelangganUIState()
    object Loading : PelangganUIState()
}
class HalamanViewModel(private val sewaRepository: SewaRepository): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUIState: StateFlow<HomeUIState> = sewaRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listPelanggan = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )
}