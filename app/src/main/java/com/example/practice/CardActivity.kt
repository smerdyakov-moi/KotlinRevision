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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.CardBack
import com.example.practice.ui.theme.PracticeTheme
import com.example.practice.ui.theme.Unique

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           CardHome()
        }
    }
}

@Composable
fun CardHome(){
    Scaffold { padding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CardBack)
                .padding(padding)
        ){
            Row(modifier = Modifier.padding(horizontal=10.dp).padding(top=10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End ){
                Image(
                    painter = painterResource(R.drawable.p1),
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )
            }
            Spacer(modifier=Modifier.padding(top=15.dp))
            Column (modifier = Modifier.padding(horizontal=10.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){
                Text(
                    "Banking App", fontSize = 28.sp,fontWeight= FontWeight.Bold, color = Color.White

                )
                Text(
                    "Good Morning, Mr. Advait Khati.",color=Color.White
                )
            }
            Spacer(modifier = Modifier.padding(top=25.dp))
            Row(
               modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.book,"Wrestler","Your dedication is unwavering.")
                Spacer(modifier=Modifier.width(15.dp))
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.card,"Type Shi","This shi is so tuff imma bus")
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.cc,"Wrestler","Your dedication is unwavering.")
                Spacer(modifier=Modifier.width(15.dp))
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.church,"Type Shi","This shi is so tuff imma bus")
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.key,"Wrestler","Your dedication is unwavering.")
                Spacer(modifier=Modifier.width(15.dp))
                BasicCard(modifier=Modifier.height(145.dp).weight(1f),R.drawable.logistics,"Type Shi","This shi is so tuff imma bus")
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
                //Final Settings Card
                Card(
                    modifier = Modifier.fillMaxWidth().height(100.dp),shape=RoundedCornerShape(10.dp),
                    colors=CardDefaults.cardColors(containerColor=Color.White),elevation=CardDefaults.cardElevation(15.dp)
                ) {
                    Row(modifier = Modifier.padding(25.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ){
                        Image(painter = painterResource(R.drawable.settings),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop)
                        Text("Settings", fontSize = 25.sp)
                    }
                }
            }

        }
    }
}

@Composable
fun BasicCard(modifier:Modifier,image:Int,title:String,description:String){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(text=title,fontWeight = FontWeight.Bold, color = Unique)
            Text(text=description,fontWeight = FontWeight.Medium, color = Unique, textAlign = TextAlign.Center)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CardHome()
}