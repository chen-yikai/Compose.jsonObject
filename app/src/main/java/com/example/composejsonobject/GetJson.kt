package com.example.composejsonobject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GetJson(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val musicList = remember { mutableStateListOf<Music>() }

    LaunchedEffect(Unit) {
        musicList.addAll(parseMusic(context))
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        contentPadding = innerPadding
    ) {
        items(musicList) {
            Card(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        it.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(it.metadata.description)
                    Spacer(Modifier.height(10.dp))
                    LazyRow {
                        items(it.metadata.tags) { tag ->
                            Card(
                                modifier = Modifier.padding(end = 10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {
                                Text(tag, modifier = Modifier.padding(5.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
