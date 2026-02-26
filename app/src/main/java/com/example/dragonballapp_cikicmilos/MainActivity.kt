package com.example.dragonballapp_cikicmilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dragonballapp_cikicmilos.presentation.navigation.AppNavHost
import com.example.dragonballapp_cikicmilos.presentation.theme.DragonBallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonBallTheme {
                AppNavHost()
            }
        }
    }
}
