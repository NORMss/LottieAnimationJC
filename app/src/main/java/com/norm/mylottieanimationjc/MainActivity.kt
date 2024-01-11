package com.norm.mylottieanimationjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.norm.mylottieanimationjc.ui.theme.MyLottieAnimationJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLottieAnimationJCTheme {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("lottie_2.json")
                )
                LottieAnimationScreen(composition)
            }
        }
    }
}

@Composable
fun LottieAnimationScreen(composition: LottieComposition?) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment
            .CenterHorizontally
    ) {
        var isPlaying by remember {
            mutableStateOf(false)
        }
        var isComplete by remember {
            mutableStateOf(false)
        }
        val animSpec = LottieClipSpec.Progress(
            min = 0.0f,
            max = if (isComplete) 1f else 0.27f
        )

        LottieAnimation(
            composition = composition,
            isPlaying = isPlaying,
            speed = 0.5f,
            iterations = if (isComplete) 1 else LottieConstants.IterateForever,
            reverseOnRepeat = true,
            clipSpec = animSpec
        )
        Button(onClick = {
            isPlaying = true
        }) {
            Text(text = "Start animation")
        }
        Button(onClick = {
            isComplete = true
        }) {
            Text(text = "Stop animation")
        }
        Button(onClick = {
            isComplete = false
            isPlaying = false
        }) {
            Text(text = "Restart")
        }
    }
}