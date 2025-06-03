package com.example.composejsonobject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composejsonobject.ui.theme.ComposejsonObjectTheme
import org.json.JSONObject
import kotlin.concurrent.timerTask

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
    val jsonObject = JSONObject(jsonString)
    val total = jsonObject.getInt("totalResults")
    val resultPage = jsonObject.getJSONArray("resultPage")
    val uri = resultPage.getJSONObject(0).getString("uri")


    Column(modifier = Modifier.statusBarsPadding()) {
        Text(total.toString())
        Text(uri)
    }
}