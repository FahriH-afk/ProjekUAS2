package com.example.projekuas.ui.theme.EditBarang

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekuas.navigation.DestinasiNavigasi
import com.example.projekuas.ui.theme.AddPelanggan.EntryBody
import com.example.projekuas.ui.theme.PelangganTopAppBar
import com.example.projekuas.ui.theme.PenyediaViewModel
import kotlinx.coroutines.launch

object EditBarangDestionation : DestinasiNavigasi {
    override val route = "barang_edit"
    override val titleRes = "Edit Barang"
    const val sewaId = "barangId"
    val routeWithArgs = "$route{$sewaId}}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayarEdit (
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditBarangBiewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            PelangganTopAppBar(
                title = EditBarangDestionation.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            addUIState = viewModel.sewaaUIState,
            onBarangValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateBarangSewa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}