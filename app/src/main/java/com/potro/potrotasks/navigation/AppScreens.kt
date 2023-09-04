package com.potro.potrotasks.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.potro.potrotasks.R
sealed class AppScreens (
    val route:String,
    val title : String,
    val icon : Int? = null
    ){
    //object SplashScreen : AppScreens("splash_screen", title = "Splash", icon = null)
    object SignIn : AppScreens("signin_screen", title = "Sign", icon = null)
    object HomeActivity : AppScreens("main_screen", title = "Home", icon = R.drawable.home)
    object CalendarScreen : AppScreens("calendar_screen", title = "Calendar", icon = R.drawable.calendar)
    object FocusScreen : AppScreens("focus_screen", title = "Focus", icon = R.drawable.focus)
    object ProfileScreen : AppScreens("profile_screen", title = "Profile", icon = R.drawable.profile)

}