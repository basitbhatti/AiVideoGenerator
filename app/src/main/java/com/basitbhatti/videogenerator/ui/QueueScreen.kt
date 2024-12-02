package com.basitbhatti.videogenerator.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basitbhatti.videogenerator.R
import com.basitbhatti.videogenerator.db.TextRequest
import com.basitbhatti.videogenerator.utils.STATUS_IN_QUEUE
import com.basitbhatti.videogenerator.utils.STATUS_SUCCESS
import com.basitbhatti.videogenerator.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun QueueScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateList()
            viewModel.updateRequests()
        }
    }

    val requests = viewModel.listRequests.observeAsState()

    requests.value?.forEach {
        Log.d("TAGRequests", "${it.prompt}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "Queue",
                modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 15.dp),
                fontSize = 18.sp
            )
        }

        if (requests.value != null) {
            LazyColumn {
                items(requests.value!!) { item ->
                    QueueItem(item = item)
                }
            }
        }
    }

}


@Composable
fun QueueItem(modifier: Modifier = Modifier, item: TextRequest) {

    val context = LocalContext.current

    val statusBg = when (item.requestStatus) {

        STATUS_SUCCESS -> {
            Color(0xFFBDEEB9)
        }

        STATUS_IN_QUEUE -> {
            Color(0xFFEEDFB9)
        }

        else -> {
            Color(0xFFEEB9B9)
        }

    }

    Card(
        modifier = modifier
            .padding(vertical = 7.5.dp, horizontal = 15.dp)
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F3F3)
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

                    if (item.requestStatus.equals(STATUS_SUCCESS)) {

                        Spacer(Modifier.width(10.dp))

                        Row(
                            modifier = Modifier
                                .clickable {
                                    val intent = Intent(ACTION_VIEW)
                                    intent.setData(Uri.parse(item.url))
                                    context.startActivity(intent)
                                }
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color.LightGray)
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Image(
                                painter = painterResource(R.drawable.ic_play),
                                contentDescription = "Play",
                                modifier = Modifier.size(16.dp)
                            )

                            Text(
                                text = "View Result",
                                modifier
                                    .padding(horizontal = 5.dp),
                                fontSize = 12.sp
                            )

                        }


                    }


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
            STATUS_IN_QUEUE,
            "inQueue",
            "1234"
        )
    )
}