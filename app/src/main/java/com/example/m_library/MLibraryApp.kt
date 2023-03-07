package com.example.m_library

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MLibraryApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }

}