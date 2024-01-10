package com.example.projekuas.ui.theme

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projekuas.ui.theme.HalamanHomeView.HalamanViewModel
import com.example.projekuas.ui.theme.AddPelanggan.PelangganViewModel
import com.example.projekuas.ui.theme.EditPelanggan.EditPelangganViewModel
import com.example.projekuas.PelangganApplication
import com.example.projekuas.ui.theme.DetailViewMoedlPelanggan.DetailPelangganViewModel

fun CreationExtras.aplikasiPelanggan(): PelangganApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PelangganApplication)
object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            PelangganViewModel(aplikasiPelanggan().container.sewaRepository)
        }
        initializer {
            HalamanViewModel(aplikasiPelanggan().container.sewaRepository)
        }
        initializer {
            DetailPelangganViewModel(
                createSavedStateHandle(),
                aplikasiPelanggan().container.sewaRepository
            )
        }
        initializer {
            EditPelangganViewModel(
                createSavedStateHandle(),
                aplikasiPelanggan().container.sewaRepository
            )
        }
    }
}