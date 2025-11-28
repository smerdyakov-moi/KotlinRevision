package com.example.practice

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.practice.ui.theme.Grey
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
    
    var selectedDate by remember { mutableStateOf("") }
    //Terms and Conditions
    var terms by remember {mutableStateOf(false)}

    //Date
    val context = LocalContext.current
    //val activity = context as Activity
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
                    val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
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


