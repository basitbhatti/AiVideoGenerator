package com.basitbhatti.videogenerator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basitbhatti.videogenerator.R
import com.basitbhatti.videogenerator.db.TextRequest
import com.basitbhatti.videogenerator.utils.STATUS_IN_QUEUE
import com.basitbhatti.videogenerator.utils.STATUS_SUCCESS
import com.basitbhatti.videogenerator.viewmodel.MainViewModel

@Composable
fun QueueScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {


}


@Composable
fun QueueItem(modifier: Modifier = Modifier, item: TextRequest) {


    val statusBg = when (item.requestStatus) {
        STATUS_SUCCESS -> {
            MaterialTheme.colorScheme.primary
        }

        STATUS_IN_QUEUE -> {
            MaterialTheme.colorScheme.secondary
        }

        else -> {
            MaterialTheme.colorScheme.tertiary
        }
    }


    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {


        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.video),
                contentDescription = "Icon Video",
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(75.dp),
            )

            Column {
                Text(text = item.prompt, fontSize = 16.sp, maxLines = 2)
                Spacer(Modifier.height(10.dp))
                Row {
                    Text(
                        text = item.requestStatus,
                        modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(statusBg)
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 14.sp
                    )
                }
            }

        }


    }
}

@Preview
@Composable
private fun Preview() {
    QueueItem(
        item =
        TextRequest(
            0,
            "Create an ad video for my clothing brand",
            STATUS_SUCCESS,
            "inQueue",
            "1234"
        )
    )
}