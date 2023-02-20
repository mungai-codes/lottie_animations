package com.example.lottiefiles

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
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
        val spec = LottieCompositionSpec.RawRes(resId = R.raw.like)
        //Using an url instead of a local file
        val spec2 =
            LottieCompositionSpec.Url("https://assets8.lottiefiles.com/packages/lf20_jKO4EX9d7w.json")
        /*
        The composition is responsible for rendering the json file and
        translate it into an animation.
         */
        val composition by rememberLottieComposition(spec = spec)
        /*
        Next we need an object that allows manipulation of the state of progression
        of the animation.
         */
        val state = animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,//the animation will now iterate forever
            speed = 1f,//speeding up the whole animation
            clipSpec = LottieClipSpec.Progress(
                min = 0f,
                max = 1f
            )//controlling the progress of an animation i.e. a like animation
        )
        //using custom progress state, set this as the progress value in the Lottie Animation composable.
        var progress by remember {
            mutableStateOf(0f)
        }
        //on certain action animations
        var isLiked by remember {
            mutableStateOf(false)
        }
        val progress2 by animateFloatAsState(
            targetValue = if (isLiked) 0.9f else 0f,
            animationSpec = tween(1500)
        )

        /*
        Finally adding a lottie composable to display the animation
         */
        LottieAnimation(
            composition = composition,
            progress = progress2,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                isLiked = !isLiked
            }
        )

        //a slider to set the progress state
        Slider(value = progress, onValueChange = { progress = it })
    }
}