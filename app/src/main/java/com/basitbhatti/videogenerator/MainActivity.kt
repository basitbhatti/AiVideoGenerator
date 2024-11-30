package com.basitbhatti.videogenerator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.basitbhatti.videogenerator.db.RequestDatabase
import com.basitbhatti.videogenerator.navigation.NavGraph
import com.basitbhatti.videogenerator.navigation.NavScreen
import com.basitbhatti.videogenerator.repository.RequestRepository
import com.basitbhatti.videogenerator.ui.theme.AiVideoGeneratorTheme
import com.basitbhatti.videogenerator.viewmodel.MainVMFactory
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiVideoGeneratorTheme {
                val dao = RequestDatabase.getInstance(this@MainActivity).dao()
                val repo = RequestRepository(dao)

                val viewModel = ViewModelProvider(
                    this@MainActivity,
                    MainVMFactory(repo)
                ).get(MainViewModel::class.java)

                Column {

                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) {
                        NavGraph(navController = navController, viewModel = viewModel)
                    }

                }
            }
        }
    }


}


@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavHostController) {

    val screens = listOf(
        NavScreen.Home,
        NavScreen.Queue
    )

    val navBackState by navController.currentBackStackEntryAsState()
    val currentDestination = navBackState?.destination

    NavigationBar {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "Navigation Icon") },
                label = { Text(text = item.label) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }

}
