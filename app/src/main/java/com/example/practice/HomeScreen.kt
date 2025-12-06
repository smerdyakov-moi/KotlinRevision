package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.BG
import com.example.practice.ui.theme.Grey
import com.example.practice.ui.theme.Orange
import com.example.practice.ui.theme.Pink80
import com.example.practice.ui.theme.PracticeTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen1()
        }
    }
}

@Composable
fun HomeScreen1(){
    data class Product(val image: Int, val label: String)
    val listData = listOf(
        Product(R.drawable.key,"Key"),
        Product(R.drawable.cc,"Certificate"),
        Product(R.drawable.book,"Book"),
        Product(R.drawable.card,"Card"),
        Product(R.drawable.pic,"Picture"),
        Product(R.drawable.settings,"Setting")
    )
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item { //static
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BG)
                    .padding(16.dp)
            ){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    shape = RoundedCornerShape(12.dp),
                    onValueChange = {},
                    label = { Text("Search Item") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_headphones_24),
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), shape = RoundedCornerShape(10.dp),
                    colors=CardDefaults.cardColors(containerColor=Pink80),elevation=CardDefaults.cardElevation(15.dp),
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(7.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.baseline_location_on_24),
                            contentDescription = null,
                            tint = Orange
                        )
                        Column {
                            Text("Location")
                            Text("Allow permissions to turn on", color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop

                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp))
                {
                    items(listData){
                            product ->
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Image(painter = painterResource(product.image),
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(product.label)
                        }
                    }
                }


        }
        }

        item{
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ){
                Text("Flash Sale",fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,textAlign = TextAlign.Left,fontSize = 25.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Sale ends soon!!!",color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("25 items left",color = Orange)
                    Icon(
                        painter=painterResource(R.drawable.baseline_keyboard_arrow_right_24),contentDescription=null,tint = Orange
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp))
                {
                    items(listData){
                            product ->
                        Card(
                            modifier = Modifier.width(150.dp),
                            colors=CardDefaults.cardColors(containerColor=Grey.copy(alpha = 0.2f))){
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ){
                                Image(
                                    painter = painterResource(product.image),
                                    contentDescription=product.label,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Sonic Headphones",fontSize=16.sp,fontWeight= FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        "PKR 800",
                                        color = Orange,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        "PKR 1300",
                                        color = Color.Gray,
                                        textDecoration = TextDecoration.LineThrough,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Recommended",fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,textAlign = TextAlign.Left,fontSize = 25.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("You would definitely like this!!",color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Check more!!",color = Orange)
                    Icon(
                        painter=painterResource(R.drawable.baseline_keyboard_arrow_right_24),contentDescription=null,tint = Orange
                    )
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp))
                {
                    items(listData){
                            product ->
                        Card(
                            modifier = Modifier.width(150.dp),
                            colors=CardDefaults.cardColors(containerColor=Grey.copy(alpha = 0.2f))){
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ){
                                Image(
                                    painter = painterResource(product.image),
                                    contentDescription=product.label,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Sonic Headphones",fontSize=16.sp,fontWeight= FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        "PKR 800",
                                        color = Orange,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        "PKR 1300",
                                        color = Color.Gray,
                                        textDecoration = TextDecoration.LineThrough,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    HomeScreen1()
}
