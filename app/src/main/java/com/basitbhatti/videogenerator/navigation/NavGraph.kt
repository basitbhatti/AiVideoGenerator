package com.basitbhatti.videogenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.basitbhatti.videogenerator.ui.HomeScreen
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController : NavHostController,
    viewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = NavScreen.Home.route){
        composable(route = NavScreen.Home.route){
            HomeScreen(viewModel = viewModel)
        }
    }

}