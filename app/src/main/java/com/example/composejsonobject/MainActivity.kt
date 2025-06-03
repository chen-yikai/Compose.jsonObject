package com.example.composejsonobject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composejsonobject.ui.theme.ComposejsonObjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposejsonObjectTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val musicList = remember { mutableStateListOf<Music>() }

    LaunchedEffect(Unit) {
        musicList.addAll(parseMusic(context))
    }

    LazyColumn(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        items(musicList) {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(it.name)
                Row {
                    it.metadata.tags.forEach { tag ->
                        Card(modifier = Modifier.padding(end = 3.dp)) {
                            Text(tag, modifier = Modifier.padding(5.dp))
                        }
                    }
                }
            }
        }
    }
}