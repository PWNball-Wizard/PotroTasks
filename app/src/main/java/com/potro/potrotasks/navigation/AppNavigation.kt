package com.potro.potrotasks.navigation
import SignInView
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.potro.potrotasks.presentation.home.HomeActivity
import com.potro.potrotasks.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.SignIn.route){
            SignInView(navController)
        }
        composable(AppScreens.MainActivity.route){
            HomeActivity()
        }
    }
}
