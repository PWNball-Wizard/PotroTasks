package com.potro.potrotasks

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.navigation.AppNavigation
import com.potro.potrotasks.navigation.AppScreens
import com.potro.potrotasks.navigation.AppScreens.*
import com.potro.potrotasks.navigation.BottomNavigationItems
import com.potro.potrotasks.navigation.BottomNavigationMenu
import com.potro.potrotasks.ui.theme.PotroTasksTheme

class MainActivity : ComponentActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            //MainPantalla()
            //mainScreen()
        //}
            PotroTasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val navController = rememberNavController()
                    //AppNavigation()
                    mainFS()
                    //xd(navController = rememberNavController())
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun mainFS(){

    val navController = rememberNavController()

    val items = listOf(
        HomeActivity,
        CalendarScreen,
        FocusScreen,
        ProfileScreen
        /*BottomNavigationItems(
            route = "main_screen",
            icon = R.drawable.home,
            title = "Home"
        ),
        BottomNavigationItems(
            route = "calendar_screen",
            icon = R.drawable.calendar,
            title = "Profile"
        ),
        BottomNavigationItems(
            route = "focus_screen",
            icon = R.drawable.focus,
            title = "Settings"
        ),
        BottomNavigationItems(
            route = "profile_screen",
            icon = R.drawable.profile,
            title = "Profile"
        ),*/
    )

    Scaffold(
        bottomBar = {
            //BottomNavigationMenu(navController = navController)
            BottomNavigationMenu(navController = navController, items = items)
        }
    )
    {
        AppNavigation(navController = navController)

        var activityToLaunch =
            if(Firebase.auth.currentUser != null){
                AppScreens.HomeActivity.route
            }
            else{
                AppScreens.SignIn.route
            }
        navController.navigate(activityToLaunch)
    }
}

/*@ExperimentalMaterial3Api
@Composable
fun TopBar() {
    //creamos un top bar
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.purple_500),
        )
    )
}*/

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPantalla(){
    val navController = rememberNavController()
    //para mostrar el splash al inicio de la app debemos crear un mutableStateOf
    //y pasarlo como parametro a la pantalla de splash
    var splash by rememberSaveable {
        mutableStateOf(true)
    }

    val navItems = listOf(
        //SplashScreen,
        //SignIn,
        MainActivity,
        CalendarScreen,
        FocusScreen,
        ProfileScreen
    )
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
               BottomNavigationMenu(navController, navItems)
        },
        modifier = Modifier.background(color = Color.White)
    )
    {
        //para quitar el error Content padding parameter it is not used
        //le pasamos un modifier
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                color = Color.White
            )
        }
        AppNavigation(navController = navController, startDestination = "splash_screen")
    }*/
//}
