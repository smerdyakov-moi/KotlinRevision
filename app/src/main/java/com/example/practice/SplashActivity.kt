package com.example.practice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen()
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val activity = context as? Activity
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rentr))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 2
    )

    LaunchedEffect(progress) {
        if (progress == 1f) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            activity?.finish()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview
@Composable
fun PreviewSPlash(){
    SplashScreen()
}
