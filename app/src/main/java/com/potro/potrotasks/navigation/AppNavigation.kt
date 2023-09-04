package com.potro.potrotasks.navigation
import SignInView
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.potro.potrotasks.presentation.home.HomeActivity
import com.potro.potrotasks.presentation.screens.CalendarScreen
import com.potro.potrotasks.presentation.screens.FocusScreen
import com.potro.potrotasks.presentation.screens.ProfileScreen
import com.potro.potrotasks.presentation.splash.SplashScreen


@ExperimentalMaterial3Api
@Composable
fun AppNavigation(navController: NavHostController) {
    //val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HomeActivity.route) {
        //indicamos que la pantalla de inicio es la splash screen
        //y le pasamos el navController como parametro
        //ejemplo: composable(AppScreens.SplashScreen.route){
        //    SplashScreen(navController)
        //}
        /*composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }*/
        composable(AppScreens.SignIn.route){
            SignInView(navController)
        }
        composable(AppScreens.HomeActivity.route){
            HomeActivity(navController)
        }
        composable(AppScreens.CalendarScreen.route){
            CalendarScreen(navController)
        }
        composable(AppScreens.FocusScreen.route){
            FocusScreen(navController)
        }
        composable(AppScreens.ProfileScreen.route){
            ProfileScreen(navController)
        }
    }
}
