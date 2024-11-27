package com.basitbhatti.videogenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.ViewModelProvider
import com.basitbhatti.videogenerator.db.RequestDatabase
import com.basitbhatti.videogenerator.repository.RequestRepository
import com.basitbhatti.videogenerator.ui.HomeScreen
import com.basitbhatti.videogenerator.ui.theme.AiVideoGeneratorTheme
import com.basitbhatti.videogenerator.viewmodel.MainVMFactory
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiVideoGeneratorTheme {

                Column {

                    val dao = RequestDatabase.getInstance(this@MainActivity).dao()
                    val repo = RequestRepository(dao)

                    val viewModel = ViewModelProvider(
                        this@MainActivity,
                        MainVMFactory(repo)
                    ).get(MainViewModel::class.java)

                    HomeScreen(viewModel = viewModel)

                }
            }
        }
    }


}
