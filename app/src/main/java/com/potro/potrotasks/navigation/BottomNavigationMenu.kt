package com.potro.potrotasks.navigation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.potro.potrotasks.R

@ExperimentalMaterial3Api
@Composable
fun BottomNavigationMenu(navController: NavController, items: List<AppScreens>){
    val currentRoute = currentRoute(navController)

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
    NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            //_selectedItemIndex = index
                            //currentRoute == item.route
                            navController.navigate(item.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true//permite que si el usuario esta en la pantalla de inicio y le da click al icono de inicio no se vuelva a cargar la pantalla de inicio

                            }
                            //Toast.makeText(context, item.route, Toast.LENGTH_SHORT).show()

                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon!!),
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title, fontSize = 12.sp)
                        },
                        alwaysShowLabel = false
                    )
                }
            }
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
}

@Composable
private fun currentRoute(navController: NavController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: AppScreens.HomeActivity.route
}