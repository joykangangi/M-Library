package com.example.m_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.m_library.app.navigation.Navigation
import com.example.m_library.app.theme.MLibraryTheme

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLibraryTheme {
                Navigation()
            }
        }
    }
}