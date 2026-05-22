package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.navigation.NavGraph
import com.example.ui.RestoranViewModel
import com.example.ui.theme.RestoranTheme

class MainActivity : ComponentActivity() {
    private val viewModel: RestoranViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            RestoranTheme(darkTheme = uiState.isDarkTheme) {
                // Each screen has its own Scaffold to manage top/bottom bars and insets properly.
                // We remove the outer Scaffold to avoid inset consumption issues.
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh preferences data whenever app gains focus
        viewModel.loadDataFromPrefs()
    }
}
