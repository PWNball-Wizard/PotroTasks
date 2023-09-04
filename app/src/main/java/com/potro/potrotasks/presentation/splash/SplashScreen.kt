package com.potro.potrotasks.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.MainActivity
import kotlinx.coroutines.delay
import com.potro.potrotasks.R
import com.potro.potrotasks.navigation.AppScreens
import com.potro.potrotasks.navigation.AppScreens.*

@Composable
fun SplashScreen(navController: NavController) {}
    //para lograr que esta actividad se ejecute antes de la MainActivity
    //se debe modificar el AndroidManifest.xml y agregar la siguiente linea
    //de codigo en la actividad que se quiere ejecutar primero
    //<intent-filter>
    //    <action android:name="android.intent.action.MAIN" />
    //    <category android:name="android.intent.category.LAUNCHER" />
    //</intent-filter>

    /*var activityToLaunch =
        if(Firebase.auth.currentUser != null){
            AppScreens.HomeActivity.route
        }
        else{
            AppScreens.SignIn.route
        }


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
        delay(1L)
        navController.popBackStack()
        navController.navigate(activityToLaunch)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(Color.White)){
    }*/
    /*val scale = remember { Animatable(0f) }

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
    }*/
//}