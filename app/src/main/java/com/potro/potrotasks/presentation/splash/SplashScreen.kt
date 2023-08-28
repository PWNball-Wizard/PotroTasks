package com.potro.potrotasks.presentation.splash

import android.content.Context
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.potro.potrotasks.MainActivity
import kotlinx.coroutines.delay
import com.potro.potrotasks.R
import com.potro.potrotasks.navigation.AppScreens


@Composable
fun SplashScreen(navController : NavController) {

    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(AppScreens.SignIn.route)
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.potro_splash), contentDescription = "Potro Splash")
        Text(text = "POTRO TASKS", color = Color.Black, fontSize = 30.sp)
    }
    //navController.navigate(AppScreens.MainActivity.route)
    /*Column(modifier = Modifier
        .fillMaxSize()) {

    }*/
    /*var activityToLaunch =
        if(Firebase.auth.currentUser != null) "home_screen"
        else "signin_screen"

    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        //navController.navigate(MainActivity)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){

        Image(painter = painterResource(id = R.drawable.potro_splash),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }*/
}

/*@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PotroTasksTheme {
        //le damos el contexto de la app para que no de error
        SplashScreen(navController = NavController(LocalContext.current))
    }
}*/