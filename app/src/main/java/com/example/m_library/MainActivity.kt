package com.example.m_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.m_library.navigation.CustomBottomNav
import com.example.m_library.ui.theme.MLibraryTheme
import com.example.m_library.viewmodel.AddEditViewModel
import com.example.m_library.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val bookViewModel: BookViewModel by viewModels()
//        val addEditViewModel: AddEditViewModel by viewModels()

        setContent {
            MLibraryTheme {

                CustomBottomNav()
            }
        }
    }
}