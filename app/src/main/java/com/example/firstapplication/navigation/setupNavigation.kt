package com.example.firstapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstapplication.model.SupportModel
import com.example.firstapplication.ui.screen.CategoryScreen
import com.example.firstapplication.ui.screen.FeedbackScreen
import com.example.firstapplication.ui.screen.HomeScreen
import com.example.firstapplication.ui.screen.LoginScreen
import com.example.firstapplication.ui.screen.ProfileScreen
import com.example.firstapplication.ui.screen.SelectedProductScreen
import com.example.firstapplication.ui.screen.SplashScreen
import com.example.firstapplication.viewModel.ProductViewModel


@Composable
fun SetupNavigation(
    navController: NavHostController,
    search: MutableState<String>,
    userName: MutableState<String>,
    phoneNum: MutableState<String>,
    email: MutableState<String>,
    viewModel: ProductViewModel
) {

    NavHost(
        navController = navController ,
        startDestination = Screens.Splash.route
    ) {
        composable(
            route = Screens.Splash.route
        ){ SplashScreen(navController) }

        composable(
            route = Screens.Login.route
        ){ LoginScreen(userName = userName, phoneNum = phoneNum, navController) }

        composable(
            route = Screens.Home.route
        ){ HomeScreen(search, navController,
            viewModel = viewModel) }

        composable(
            route = Screens.Category.route
        ){ CategoryScreen(navController) }

        composable(
            route = Screens.Profile.route
        ){ ProfileScreen(navController = navController,
            userName = userName,
            phoneNum = phoneNum,
            email = email) }

        composable(
            route = Screens.SelectedProduct.route
        ){ SelectedProductScreen(navController = navController,
            viewModel = viewModel) }

        composable(
            route = Screens.Feedback.route
        ){ FeedbackScreen(navController = navController) }

    }
}

