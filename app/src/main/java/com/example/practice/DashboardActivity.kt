package com.example.practice

import android.app.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice.ui.theme.BG
import com.example.practice.ui.theme.PracticeTheme
import com.example.practice.ProfileScreen1
import com.google.common.collect.Multimaps.index

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBody() {
    val context = LocalContext.current
    val activity = context as? Activity

    data class BottomNavItem(val label: String, val icon:Int)
    val bottomNavitems = listOf(
        BottomNavItem("Home",R.drawable.baseline_home_24),
        BottomNavItem("Search",R.drawable.baseline_search_24),
        BottomNavItem("Profile",R.drawable.baseline_person_24)

    )

    var selectedIndex by remember {mutableStateOf(0)}

    //val userName = activity.intent.getStringExtra("userName")

    Scaffold(
        bottomBar = {
            NavigationBar{
                bottomNavitems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(item.label) },
                        icon = {
                            Icon(
                                painterResource(item.icon),
                                contentDescription = item.label
                            )
                        },
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        }
                    )

                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                            ,
            contentAlignment = Alignment.Center
        ) {
            when (selectedIndex) {
                0 -> HomeScreen1()
                1 -> SearchScreen1()
                2 -> ProfileScreen1()
            }
        }
    }
}

@Preview
@Composable
fun previewbody(){
    DashboardBody()
}

@Composable
fun Home(){
}
@Composable
fun Search() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {
        Text("Search")
    }
}

@Composable
fun Profile(){
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Red))
    {
        Text("Profile")
    }

}