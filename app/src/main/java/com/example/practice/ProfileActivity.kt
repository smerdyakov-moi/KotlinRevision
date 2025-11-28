package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
                )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_perm_media_24),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null
                )
                Text("khatipragyan14",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_more_horiz_24),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

            ){
                Image(painter = painterResource(R.drawable.p1),
                    contentDescription = null,
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text("5",)
                    Text("Posts",fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text("100")
                    Text("Followers",fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text("100")
                    Text("Following",fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .padding(horizontal = 15.dp)
            ){
                Text("Pragyan K.", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("There are many people in this world but the beauty behind your eyes is what amazes me to keep my eyes.", textAlign = TextAlign.Justify)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Button(onClick = { }, modifier = Modifier.weight(2f)) {
                    Text("Follow")
                }
                OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) {
                    Text("Message")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.padding(horizontal=16.dp)
            ){
                Text("Highlights",fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.padding(horizontal=16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            )
            {
                highlight(R.drawable.pic,"Maybe")
                highlight(R.drawable.p1,title="One")
                highlight(R.drawable.p1,title="One")
                highlight(R.drawable.p1,title="One")
            }

        }
    }
}

//For Image
@Composable
fun highlight(imageRes:Int,title:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(imageRes),
            contentDescription = title,modifier=Modifier.size(50.dp).clip(CircleShape), contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text=title)
    }
}

@Preview()
@Composable
fun GreetingPreview() {
    Greeting()
}