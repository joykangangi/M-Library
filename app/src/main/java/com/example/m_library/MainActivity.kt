package com.example.m_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.m_library.data.BookRepository
import com.example.m_library.navigation.CustomBottomNav
import com.example.m_library.ui.theme.MLibraryTheme

import com.example.m_library.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLibraryTheme {
                CustomBottomNav()
            }
        }
    }
}