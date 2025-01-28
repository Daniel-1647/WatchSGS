package com.daniel.watchsgs.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.daniel.watchsgs.R
import com.daniel.watchsgs.navigation.Screens
import com.daniel.watchsgs.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(Constants.SPLASH_DELAY)
        navController.navigate(Screens.Home.route){
            popUpTo(Screens.Home.route) {
                inclusive = true
            }
        }
    }

    Splash()
}

@Composable
fun Splash() {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val lighterSurface = surfaceColor.copy(alpha = 0.8f)
    val darkerSurface = surfaceColor.copy(alpha = 1.0f)

    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(lighterSurface, darkerSurface)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(180.dp),
            painter = painterResource(R.drawable.glasses),
            contentDescription = null
        )
    }
}