package com.example.m_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.m_library.navigation.CustomBottomNavigation
import com.example.m_library.navigation.BottomScreen
import com.example.m_library.ui.theme.MLibraryTheme
import com.example.m_library.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bookViewModel: BookViewModel by viewModels()
        setContent {

            val currentScreen = remember { mutableStateOf<BottomScreen>(BottomScreen.MyBooks) }
            MLibraryTheme {
                Scaffold(
                    bottomBar = {
                        CustomBottomNavigation(currentScreenId = currentScreen.value.id) {
                            currentScreen.value = it
                        }
                    }
                ) {

                }
            }
        }
    }
}