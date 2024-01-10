package com.example.projekuas

import android.app.Application
import com.example.projekuas.database.AppContainer
import com.example.projekuas.database.SewaContainer

class PelangganApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = SewaContainer()
    }
}