package com.example.projekuas.ui.theme

import com.example.projekuas.Model.BarangSewa
import com.example.projekuas.Model.Pelanggan

data class AddUIState(
    val detailPelanggan: DetailPelanggan = DetailPelanggan(),

    val detailBarangSewa: DetailBarangSewa = DetailBarangSewa()
)

data class DetailPelanggan(
    val IdPelanggan: String = "",
    val NamaPelanggan: String = "",
    val NomorTelepon: String = "",
    val Alamat: String = ""
)

data class DetailBarangSewa(
    val IdSewa: String = "",
    val JenisKamera: String = "",
    val JenisLensa: String = ""
)

fun DetailPelanggan.toPelanggan() = Pelanggan(
    idPelanggan = IdPelanggan,
    namaPelanggan = NamaPelanggan,
    alamatPelanggan = Alamat,
    nomorTelepon = NomorTelepon,

)

fun DetailBarangSewa.toBarang() = BarangSewa (
    idSewa = IdSewa,
    jenisKamera = JenisKamera,
    jenisLensa = JenisLensa
)

data class DetailUIState(
    val detailPelanggan: DetailPelanggan = DetailPelanggan(),

    val detailBarangSewa: DetailBarangSewa = DetailBarangSewa(),
)

fun Pelanggan.toDetailPelanggan(): DetailPelanggan =
    DetailPelanggan(
        IdPelanggan = idPelanggan,
        NamaPelanggan = namaPelanggan,
        Alamat = alamatPelanggan,
        NomorTelepon = nomorTelepon,

    )

fun BarangSewa.toDetailBarangSewa(): DetailBarangSewa =
    DetailBarangSewa(
        IdSewa = idSewa,
        JenisKamera = jenisKamera,
        JenisLensa = jenisLensa,
    )

fun Pelanggan.toUIStatePelanggan(): AddUIState = AddUIState(
    detailPelanggan = this.toDetailPelanggan()
)

fun BarangSewa.toUIStateBarangSewa(): AddUIState = AddUIState(
    detailBarangSewa = this.toDetailBarangSewa()
)

data class HomeUIState(
    val listPelanggan: List<Pelanggan> = listOf(),
    val dataLength: Int = 0,
    val listBarangSewa: List<BarangSewa> = listOf(),
    val dataLength1: Int = 0 ,
)