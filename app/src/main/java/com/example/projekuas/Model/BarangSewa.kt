package com.example.projekuas.Model

data class BarangSewa(
    val idSewa : String = "",
    val jenisKamera : String = "",
    val jenisLensa : String = ""
){
    constructor(): this ("","","")
}

