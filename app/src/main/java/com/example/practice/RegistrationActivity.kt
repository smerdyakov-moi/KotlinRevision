package com.example.practice

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.practice.ui.theme.Grey
import com.example.practice.ui.theme.Orange
import com.example.practice.ui.theme.PurpleGrey80
import java.util.Calendar

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Register()
        }
    }
}

@Composable
fun Register(){
    var userName by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    //Password Visibility
    var visibility by remember {mutableStateOf(false)}

    //Local Storage - SHared Preference
    val context = LocalContext.current
    val sharedPreference = context.getSharedPreferences("User", Context.MODE_PRIVATE) // "User" is a theoretical representation of a table but actually is a file
    val editor = sharedPreference.edit()
    
    var selectedDate by remember { mutableStateOf("") }
    //Terms and Conditions
    var terms by remember {mutableStateOf(false)}

    //Date
    val activity = context as Activity
    var calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)
    var datePicker = DatePickerDialog(
        context, { _, y, m, d ->
            selectedDate = "$d/${m + 1}/$y"

        }, year, month, day
    )

    //Dropdown Menu - 3 state variables, one immutable list
    var expanded by remember{mutableStateOf(false)}
    var selectedText by remember{mutableStateOf("Select Option")}
    val countries= listOf("Nepal","India","China","Pakistan","Bhutan")
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }




    Scaffold {
            padding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.padding(top=80.dp))

            Text("Sign Up", modifier = Modifier.fillMaxWidth(),style = TextStyle(
                color = Color.Blue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),textAlign = TextAlign.Center)

            Text(
                "Welcome to our renting marketplace, where customers are granted diversity with a great trust.",
                style = TextStyle(
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                MediaCard(modifier  = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f),image=R.drawable.fb,"Facebook")
                Spacer(modifier=Modifier.width(16.dp))
                MediaCard(modifier  = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f),image=R.drawable.gmail,"Gmail")
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it  },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                placeholder = {
                    Text("username")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Grey,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ){
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country) },
                            onClick = {
                                selectedText  = country
                                expanded = false
                            }
                        )
                    }
                }
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = {},
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned {
                            coordinates -> textFieldSize = coordinates.size.toSize()
                        }
                        .clickable {
                            expanded = true
                        },
                    placeholder = {Text("Select Country")},
                    enabled = false, //prevents manual writing
                    trailingIcon = {
                        Icon(
                           painter =  painterResource(R.drawable.baseline_perm_media_24),
                            contentDescription = null
                        )
                    }
                )

            }

            Spacer(modifier=Modifier.height(20.dp))
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { data ->
                    selectedDate = data
                },
                shape = RoundedCornerShape(12.dp),
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datePicker.show()
                    }
                    .padding(horizontal = 15.dp),
                placeholder = {
                    Text("dd/mm/yyyy")
                },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = PurpleGrey80,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = PurpleGrey80,
                    unfocusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if(!visibility) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon={
                    IconButton(onClick={
                        visibility=!visibility
                    }){
                        Icon(
                            painter= if (visibility){
                                painterResource(R.drawable.baseline_visibility_24)
                            }else{
                                painterResource(R.drawable.baseline_visibility_off_24)
                            }, contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                placeholder = {
                    Text("*********")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Grey,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier=Modifier.height(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Checkbox(
                    checked = terms,
                    onCheckedChange = {terms=it},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Green,
                        checkmarkColor = White
                    )
                )
                Text("I agree to terms & Conditions")
            }
            Button(
                onClick={
                    if(terms){
                        val storedUsername = sharedPreference.getString("username", null)
                        if (userName.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Username and password cannot be empty.", Toast.LENGTH_SHORT).show()
                        } else if (!terms){
                            Toast.makeText(context, "Please agree to the terms and conditions.",Toast.LENGTH_SHORT).show()
                        } else if (userName == storedUsername) {
                            Toast.makeText(context, "User already exists. Please login.", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            editor.putString("username",userName)
                            editor.putString("password",password)
                            editor.putString("date",selectedDate)
                            editor.apply()
                            Toast.makeText(context, "Registration Successful.", Toast.LENGTH_SHORT).show()
                            activity.finish()
                        }
                    }
                    else{
                            Toast.makeText(context, "Please agree to the terms and conditions.",Toast.LENGTH_SHORT).show()
                    }   
                     },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 15.dp, vertical = 20.dp),
            ) {
                Text("Sign Up")
            }
            Text(
                buildAnnotatedString {
                    append("Already have an account?")

                    withStyle(style = SpanStyle(color = Color.Blue,fontSize=20.sp)) {
                        append(" Log In.")
                    }
                },modifier = Modifier
                    .clickable(
                        onClick = {
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                        }
                    )
                    .padding(horizontal = 15.dp),style= TextStyle(fontSize=17.sp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Register()
}


class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileScreen1()
        }
    }
}

    @Composable
    fun ProfileScreen1() {
        data class Product(val image: Int, val label: String)
        val listData = listOf(
            Product(R.drawable.baseline_heart_broken_24, "Favorites"),
            Product(R.drawable.baseline_local_shipping_24, "Orders"),
            Product(R.drawable.baseline_list_24, "Shippings"),
            Product(R.drawable.card, "Card"),
            Product(R.drawable.pic, "Picture"),
            Product(R.drawable.settings, "Setting")
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.baseline_settings_24),
                        contentDescription = null
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(R.drawable.p1),
                        contentDescription = null,
                        contentScale = ContentScale.Companion.Crop,
                        modifier = Modifier.Companion.size(75.dp).clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(top = 6.dp))
                        Text("Pragyan Khati", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Manage Profile", color = Orange, fontWeight = FontWeight.Bold)
                    }
                }

                HorizontalDivider()
                Spacer(modifier = Modifier.padding(top = 15.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(listData) { product ->
                        Card(
                            modifier = Modifier.size(100.dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(product.image),
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(30.dp)
                                )
                                Text(product.label, fontWeight = FontWeight.Bold)
                                Text(
                                    "Click to check",
                                    color = Orange,
                                    textDecoration = TextDecoration.Companion.Underline
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 26.dp))
                Text("Order Highlights")
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

            items(listData) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(product.image),
                            modifier = Modifier.size(50.dp).clip(CircleShape),
                            contentScale = ContentScale.Companion.Crop,
                            contentDescription = null
                        )
                        Text(product.label, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }


