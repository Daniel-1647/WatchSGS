package com.daniel.watchsgs.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Home : Screens("home_screen")
    object Details : Screens("details_screen/{videoId}"){
        fun passVideoId(id: Any): String {
            return "details_screen/$id"
        }
    }
}