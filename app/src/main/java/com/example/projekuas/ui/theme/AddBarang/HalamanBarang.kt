package com.example.projekuas.ui.theme.AddBarang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekuas.navigation.DestinasiNavigasi
import com.example.projekuas.ui.theme.AddUIState
import com.example.projekuas.ui.theme.DetailBarangSewa
import com.example.projekuas.ui.theme.PelangganTopAppBar
import com.example.projekuas.ui.theme.PenyediaViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

object BarangDestination: DestinasiNavigasi {
    override val route = "barang_entry"

    override val titleRes = "Isi Data Barang Yang Akan Disewa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanBarang(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    barangViewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory),

    ) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PelangganTopAppBar(
                title = BarangDestination.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        EntryBody(
            addUIState = barangViewModel.addUIState,
            onBarangValueChange = barangViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    barangViewModel.addBarangSewa()
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

@Composable
fun EntryBody(
    addUIState: AddUIState,
    onBarangValueChange: (DetailBarangSewa) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            detailBarangSewa = addUIState.detailBarangSewa,
            onValueChange = onBarangValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    detailBarangSewa: DetailBarangSewa,
    modifier: Modifier = Modifier,
    onValueChange : (DetailBarangSewa) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = detailBarangSewa.JenisKamera,
            onValueChange = { onValueChange(detailBarangSewa.copy(JenisKamera = it)) },
            label = { Text("Jenis Kamera") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBarangSewa.JenisLensa,
            onValueChange = { onValueChange(detailBarangSewa.copy(JenisLensa = it)) },
            label = { Text("Jenis Lensa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}

