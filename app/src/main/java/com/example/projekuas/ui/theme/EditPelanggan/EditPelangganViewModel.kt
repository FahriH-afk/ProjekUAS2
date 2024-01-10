package com.example.projekuas.ui.theme.EditPelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekuas.database.SewaRepository
import com.example.projekuas.ui.theme.AddUIState
import com.example.projekuas.ui.theme.DetailPelanggan
import com.example.projekuas.ui.theme.toPelanggan
import com.example.projekuas.ui.theme.toUIStatePelanggan
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditPelangganViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: SewaRepository
) : ViewModel() {

    var sewaUiState by mutableStateOf(AddUIState())
        private set

    private val pelangganId: String = checkNotNull(savedStateHandle[EditDestination.pelangganId])

    init {
        viewModelScope.launch {
            sewaUiState =
                repository.getPelangganById(pelangganId)
                    .filterNotNull()
                    .first()
                    .toUIStatePelanggan()
        }
    }

    fun updateUIState(addEvent: DetailPelanggan) {
        sewaUiState = sewaUiState.copy(detailPelanggan = addEvent)
    }

    suspend fun updatePelanggan() {
        repository.update(sewaUiState.detailPelanggan.toPelanggan())

    }
}