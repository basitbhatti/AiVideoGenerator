package com.basitbhatti.videogenerator.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basitbhatti.videogenerator.R
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.background(Color.White),
    viewModel: MainViewModel
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var prompt by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.video),
                contentDescription = "Video Icon",
                modifier = Modifier.size(64.dp),
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Ai Video Generator",
                color = Color.Black,
                fontSize = 28.sp,
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                value = prompt,
                onValueChange = {
                    prompt = it
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                ),
                label = {
                    Text("Enter Prompt")
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 15.dp)
                    .height(50.dp),
                onClick = {
                    if (prompt.isEmpty()) {
                        Toast.makeText(context, "Empty Prompt", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.sendTextRequest(TextRequestBody(prompt))
                        showDialog = true

                    }
                }
            ) {
                Text(text = "Generate Ai Video")
            }

            CustomDialog(
                showDialog = showDialog,
                onDismissRequest = { showDialog = false }
            ) {
                ProgressDialog {
                    showDialog = false
                }
            }

        }
    }

}

@Preview
@Composable
private fun Preview() {

}