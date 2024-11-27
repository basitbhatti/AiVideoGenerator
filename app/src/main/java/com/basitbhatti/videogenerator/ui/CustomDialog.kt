package com.basitbhatti.videogenerator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.basitbhatti.videogenerator.R

@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissRequest, properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .pointerInput(Unit) { detectTapGestures { } }
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                        .width(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            MaterialTheme.colorScheme.surface,
                        ), contentAlignment = Alignment.Center) {
                    content()
                }

            }
        }
    }
}

@Composable
fun MessageDialog(modifier: Modifier = Modifier, onDismissRequest: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            Image(
                painter = painterResource(R.drawable.magic),
                contentDescription = "",
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Request in queue.", fontSize = 22.sp)
            Text(text = "We will notify you when it's done.", fontSize = 14.sp)

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 15.dp)
                    .height(50.dp),

                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = "Okay")
            }

            Spacer(modifier = Modifier.height(25.dp))

        }

    }
}


@Preview
@Composable
private fun Preview() {
    CustomDialog(true, {}) {
    }
}