package com.example.lottiefiles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val spec = LottieCompositionSpec.RawRes(resId = R.raw.black_cat_halloween)

            val composition by rememberLottieComposition(spec = spec)

            val state = animateLottieCompositionAsState(
                composition = composition,
                speed = 0.5f,
            )

            LottieAnimation(composition = composition, progress = state.progress)
            if (state.isAtEnd && state.isPlaying) {
                navController.navigate(Screen.Home.route)
            }
        }

    }
}