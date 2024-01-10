package com.example.projekuas.ui.theme.HalamanHomeView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekuas.Model.Pelanggan
import com.example.projekuas.ui.theme.PenyediaViewModel
import com.example.projekuas.navigation.DestinasiNavigasi
import com.example.projekuas.ui.theme.PelangganTopAppBar

object DestinasiHome : DestinasiNavigasi {
    override val route ="home"
    override val titleRes = "Kontak"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HalamanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PelangganTopAppBar(
                title = "Pelanggan",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemPelanggan = uiStateSiswa.listPelanggan,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSiswaClick = onDetailClick
        )
    }
}
@Composable
fun BodyHome(
    itemPelanggan: List<Pelanggan>,
    modifier: Modifier = Modifier,
    onSiswaClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPelanggan.isEmpty()) {
            Text(
                text = "Tidak ada data Pelanggan",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPelanggan(
                itemPelanggan = itemPelanggan,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onSiswaClick(it.idPelanggan) }
            )
        }
    }
}
@Composable
fun ListPelanggan(
    itemPelanggan: List<Pelanggan>,
    modifier: Modifier = Modifier,
    onItemClick: (Pelanggan) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(itemPelanggan) { index, pelanggan ->
            DataPelanggan(
                pelanggan = pelanggan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(pelanggan) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataPelanggan(
    pelanggan: Pelanggan,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = pelanggan.namaPelanggan,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = pelanggan.nomorTelepon,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = pelanggan.alamatPelanggan,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}