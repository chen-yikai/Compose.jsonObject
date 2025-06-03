package com.example.composejsonobject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.json.JSONObject
import java.lang.Exception
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONArray

@Composable
fun CreateJson(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        var list = remember { mutableStateListOf<Pair<String, String>>() }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                var name by remember { mutableStateOf("") }
                var birthday by remember { mutableStateOf("") }

                Text("Personal Info", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                TextField(name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(5.dp))
                TextField(birthday, onValueChange = { birthday = it }, label = { Text("Birthday") })
                Spacer(modifier = Modifier.height(20.dp))

                FilledTonalButton(onClick = {
                    list.add(Pair(name, birthday))
                    name = ""
                    birthday = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Add (${list.size})")
                }
            }
        }
        Button(onClick = {
            try {
                var jsonArray = JSONArray()
                list.forEach {
                    val jsonObject = JSONObject().apply {
                        put("name", it.first)
                        put("birthday", it.second)
                    }
                    jsonArray.put(jsonObject)
                }
                text = jsonArray.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }) {
            Text("Create a JSON")
        }
        if (text.isNotEmpty())
            Card(modifier = Modifier.padding(10.dp)) {
                Text(
                    text,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(10.dp)
                )
            }
    }
}