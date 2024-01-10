package com.example.projekuas.Model

data class Pelanggan(
    val idPelanggan: String = "",
    val namaPelanggan: String = "",
    val nomorTelepon: String = "",
    val alamatPelanggan: String = "",

){
    constructor(): this("","","","")
}
