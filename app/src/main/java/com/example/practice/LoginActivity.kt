package com.example.practice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.Grey
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginBody()
        }
    }
}


@Composable
fun LoginBody(){
    var userName by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var visibility by remember {mutableStateOf(false)}

    val context = LocalContext.current
    //val activity = context as Activity

    //Snackbar
    val snackbarHostState = remember{ SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope ()

    var rememberMe by remember {mutableStateOf(false)}

    Scaffold (
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ){
        padding->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                Spacer(modifier = Modifier.padding(top=80.dp))

                Text("Sign In", modifier = Modifier.fillMaxWidth(),style = TextStyle(
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
                    modifier = Modifier.padding(vertical = 20.dp).padding(horizontal=20.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(15.dp)
                ){
                    MediaCard(modifier  = Modifier.fillMaxWidth().height(50.dp).weight(1f),image=R.drawable.fb,"Facebook")
                    Spacer(modifier=Modifier.width(16.dp))
                    MediaCard(modifier  = Modifier.fillMaxWidth().height(50.dp).weight(1f),image=R.drawable.gmail,"Gmail")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f)
                    )
                    Text("OR", modifier = Modifier.padding(horizontal = 20.dp))
                    HorizontalDivider(
                        modifier = Modifier.weight(1f)
                    )
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
                        focusedContainerColor = White,
                        unfocusedContainerColor = Grey,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier=Modifier.height(20.dp))
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
                        focusedContainerColor = White,
                        unfocusedContainerColor = Grey,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier=Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal=15.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement  = Arrangement.spacedBy(90.dp)){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = {rememberMe=it},
                            colors = CheckboxDefaults.colors(
                                checkedColor = Green,
                                checkmarkColor = White
                            )
                        )
                        Text("Remember Me")
                    }

                    Text("Forgot Password?",style=TextStyle(color=Color.Gray.copy(0.5f)))

                }
                Button(
                    onClick={
                        if(password=="correct"){
                            val intent = Intent(context, DashboardActivity::class.java)
                            intent.putExtra("userName", userName)
                            context.startActivity(intent)
                            //activity.finish()
                        }else{
                            coroutineScope.launch{
                                snackbarHostState.showSnackbar("Incorrect credentials!!")
                            }
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
                    Text("Log In")
                }
                Text(
                    buildAnnotatedString {
                        append("Don't have  an account?")

                        withStyle(style = SpanStyle(color = Color.Blue,fontSize=20.sp)) {
                            append("  Sign Up.")
                        }
                    },modifier = Modifier.clickable{
                        val intent = Intent(context, RegistrationActivity::class.java)
                        context.startActivity(intent)
                    }.padding(horizontal=15.dp),style= TextStyle(fontSize=17.sp))
            }


    }
}
@Composable
fun MediaCard(modifier:Modifier, image:Int, title:String){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(15.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp).clip(CircleShape), contentScale = ContentScale.Crop)
            Text(text=title, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    LoginBody()
}

