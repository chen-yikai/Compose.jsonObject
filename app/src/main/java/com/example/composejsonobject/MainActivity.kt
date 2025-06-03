package com.example.composejsonobject

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composejsonobject.ui.theme.ComposejsonObjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposejsonObjectTheme {
                val navController = rememberNavController()
                val bar = mapOf(
                    Screens.GetJson.name to Icons.Default.Done,
                    Screens.CreateJson.name to Icons.Default.Add
                )
                val currentBackStack by navController.currentBackStackEntryFlow.collectAsState(null)

                Scaffold(bottomBar = {
                    NavigationBar {
                        bar.forEach {
                            NavigationBarItem(
                                selected = currentBackStack?.destination?.route == it.key,
                                onClick = { navController.navigate(it.key) },
                                icon = { Icon(it.value, contentDescription = null) },
                                label = { Text(it.key) })
                        }
                    }
                }) { innerPadding ->
                    Column(modifier = Modifier.padding()) {
                        NavHost(navController, startDestination = Screens.GetJson.name) {
                            composable(Screens.GetJson.name) { GetJson(innerPadding) }
                            composable(Screens.CreateJson.name) { CreateJson(innerPadding) }
                        }
                    }
                }
            }
        }
    }
}

enum class Screens {
    GetJson, CreateJson
}