package com.basitbhatti.videogenerator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import com.basitbhatti.videogenerator.ui.HomeScreen
import com.basitbhatti.videogenerator.ui.theme.AiVideoGeneratorTheme
import com.basitbhatti.videogenerator.utils.NetworkResponse
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiVideoGeneratorTheme {

                Column {

                    val viewModel by viewModels<MainViewModel>()

                    HomeScreen(viewModel = viewModel)

                    viewModel.response.observe(this@MainActivity){ response->
                        when(response){
                            is NetworkResponse.Success -> Log.d("TAGRESPONSE", "${response.data}")
                            is NetworkResponse.Error -> Log.d("TAGRESPONSE", "${response.message}")
                            is NetworkResponse.Loading -> Log.d("TAGRESPONSE", "Sending Request...")
                        }
                    }

                }
            }
        }
    }


}
