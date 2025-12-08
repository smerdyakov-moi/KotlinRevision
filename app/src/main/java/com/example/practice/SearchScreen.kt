package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.Grey
import com.example.practice.ui.theme.Orange

class SearchScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchScreen1()
        }
    }
}

data class Product(val image: Int, val name: String, val price: String, val originalPrice: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen1(){
    val products = listOf(
        Product(R.drawable.p1, "Crystal Headphones", "PKR 800", "PKR 1200"),
        Product(R.drawable.baseline_list_24, "Wireless Headphones", "PKR 800", "PKR 1200"),
        Product(R.drawable.baseline_heart_broken_24, "Sonic Headphones", "PKR 800", "PKR 1200"),
        Product(R.drawable.key, "Vintage Headphones", "PKR 800", "PKR 1200"),
        Product(R.drawable.baseline_local_shipping_24, "Multi Earphones", "PKR 800", "PKR 1200"),
        Product(R.drawable.settings, "Wireless Earphones", "PKR 800", "PKR 1200")
    )

    LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        value = "Headphones",
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
                            unfocusedContainerColor = Grey.copy(alpha = 0.2f)
                        )
                    )
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Showing 60 results for headphones", color = Color.Gray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text("Sort by", color = Orange, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = null, tint = Orange)
                    }
                }
            }

            items(products) { product ->
                ProductCard(product = product)
            }
        }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Grey.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(product.image),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    product.price,
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    product.originalPrice,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen1()
}
