package com.example.firstapplication.navigation

sealed class Screens(val route: String ) {
    object Splash: Screens("splash")
    object Login: Screens("login")
    object Home: Screens("home")
    object Profile: Screens("profile")
    object Category: Screens("category")
    object SelectedProduct: Screens("selectedProduct")
    object Feedback: Screens("feedback")
    object Recompose: Screens("recompose")
}