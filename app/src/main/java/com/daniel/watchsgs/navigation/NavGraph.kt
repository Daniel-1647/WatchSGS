package com.daniel.watchsgs.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daniel.watchsgs.ui.screens.details.DetailsScreen
import com.daniel.watchsgs.ui.screens.home.HomeScreen
import com.daniel.watchsgs.ui.screens.splash.SplashScreen
import com.daniel.watchsgs.ui.viewmodels.DetailsViewModel
import com.daniel.watchsgs.ui.viewmodels.MainViewModel
import com.daniel.watchsgs.util.Constants

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val detailsViewModel = hiltViewModel<DetailsViewModel>()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(
            route = Screens.Details.route,
            arguments = listOf(navArgument(name = Constants.DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) {navBackStackEntry->
            val titleId = navBackStackEntry.arguments?.getInt(Constants.DETAILS_ARGUMENT_KEY)
            titleId?.let { id->
                DetailsScreen(navController = navController, titleId = titleId, detailsViewModel = detailsViewModel)
            }
        }
    }

}