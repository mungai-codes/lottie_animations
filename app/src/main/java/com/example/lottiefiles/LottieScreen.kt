package com.example.lottiefiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*
        a spec is an object that holds
        a reference of the animation
        to be animated
         */
        val spec = LottieCompositionSpec.RawRes(resId = R.raw.black_cat_halloween)
        /*
        The composition is responsible for rendering the json file and
        translate it into an animation.
         */
        val composition by rememberLottieComposition(spec = spec)
        /*
        Next we need an object that allows manipulation of the state of progression
        of the animation.
         */
        val state = animateLottieCompositionAsState(composition = composition)
        /*
        Finally adding a lottie composable to display the animation
         */
        LottieAnimation(
            composition = composition,
            progress = state.progress
        )
    }
}