package com.potro.potrotasks.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.potro.potrotasks.R
import com.potro.potrotasks.navigation.AppNavigation
import com.potro.potrotasks.navigation.AppScreens
import com.potro.potrotasks.navigation.AppScreens.*
//import com.potro.potrotasks.presentation.splash.SplashScreen

@Composable
fun HomeActivity(navController: NavController) {

    //val context = LocalContext.current
    Text(text = "Home")

    /*TopBar(navController = navController)

    BottomNavigationMenu(navController = navController)*/

}

/*@ExperimentalMaterial3Api
@Composable
fun HomeActivity(navController: NavController) {

    //val context = LocalContext.current
    Text(text = "Home")

    /*TopBar(navController = navController)

    BottomNavigationMenu(navController = navController)*/

}*/

/*@ExperimentalMaterial3Api
@Composable
fun mainScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
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
        },
        bottomBar = {
            //BottomNavigationMenu(navController, navItems)
                    when(currentRoute(navController = navController)){
                        AppScreens.HomeActivity.route, AppScreens.CalendarScreen.route, AppScreens.FocusScreen.route, AppScreens.ProfileScreen.route -> {
                            BottomNavigationMenu(navController = navController)

                            //para evitar que la splash screen se muestre en un bucle infinito
                            //podemos crear un if
                            //var splash = true
                            BottomNavigationMenu(navController = navController)
                        }
                    }
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
        AppNavigation(navController = navController)
    }

    //Text(text = "Home")

}

/*@ExperimentalMaterial3Api
@Composable
fun TopBar() {
    //creamos un top bar

}*/

@ExperimentalMaterial3Api
@Composable
fun BottomNavigationMenu(navController: NavController/*, items: List<AppScreens>*/){
    val currentRoute = currentRoute(navController)

    /*val items = listOf(
        BottomNavigationItems(
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
        ),
    )*/

    //Para hacer que se muestr el top bar hacemos un column
    //para quitar el error Content padding parameter it is not used
    //le pasamos un modifier
    /*var _selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }*/
    /*Scaffold(
        topBar = {
            TopBar(navController)
        },
        bottomBar = {*/

    val navItems = listOf(
        //SplashScreen,
        //SignIn,
        HomeActivity,
        CalendarScreen,
        FocusScreen,
        ProfileScreen
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    //_selectedItemIndex = index
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }*/
                        /*popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }*/
                        /*launchSingleTop = true
                        restoreState = true
                    }
                    //Toast.makeText(context, item.route, Toast.LENGTH_SHORT).show()

                },
                icon = {
                    BadgedBox(badge =  {}) {
                        Icon(painter = item.icon?.let { painterResource(id = it) }!!,
                            contentDescription = item.title,
                        )
                    }
                },
                label = {
                    Text(text = item.title, fontSize = 12.sp)
                }
            )
        }
    }*/
    /*},
    modifier = Modifier.background(color = Color.White)
)*/
    /*{
        //para quitar el error Content padding parameter it is not used
        //le pasamos un modifier
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }*/
//}
