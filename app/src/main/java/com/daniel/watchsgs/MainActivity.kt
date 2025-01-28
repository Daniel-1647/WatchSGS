//Created by Daniel on 18-01-2024

package com.daniel.watchsgs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.daniel.watchsgs.navigation.Screens
import com.daniel.watchsgs.navigation.SetupNavGraph
import com.example.compose.WatchSGSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val startDestination = Screens.Splash.route

        setContent {
            WatchSGSTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController, startDestination)
            }
        }
    }
}