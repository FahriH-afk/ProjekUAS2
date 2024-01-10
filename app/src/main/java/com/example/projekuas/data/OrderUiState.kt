package com.example.projekuas.data

data class OrderUiState(
    val jumlah: Int = 0,
    val alat: String = "",
    val harga: String = "",
    val namaPelanggan: String = "",
    val nomorTelepon: String = "",
    val alamat: String = ""
){
    constructor(): this(0,"","","", "", "",)
}
