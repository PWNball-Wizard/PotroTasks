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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.AppDatabase.AppDatabase
import com.potro.potrotasks.domain.model.TasksViewModel
import com.potro.potrotasks.navigation.AppNavigation
import com.potro.potrotasks.navigation.AppScreens
import com.potro.potrotasks.navigation.AppScreens.*
import com.potro.potrotasks.navigation.BottomNavigationItems
import com.potro.potrotasks.navigation.BottomNavigationMenu
import com.potro.potrotasks.presentation.task.TaskViewModel
import com.potro.potrotasks.ui.theme.PotroTasksTheme

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var database: AppDatabase
    }

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).fallbackToDestructiveMigration().build()

        installSplashScreen()
        setContent {
            PotroTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainFS()
                 }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun mainFS() {
    val navController = rememberNavController()
    val notesVM: TaskViewModel = viewModel(modelClass = TaskViewModel::class.java)

    // Inicializa el estado de autenticación aquí
    val isAuthenticated = Firebase.auth.currentUser != null

    // Manejar la navegación inicial y la configuración de la barra de navegación inferior
    Scaffold(
        bottomBar = {
            // Comprueba si el usuario está autenticado antes de mostrar la barra de navegación
            if (isAuthenticated) {
                BottomNavigationMenu(navController = navController, items = listOf(
                    HomeActivity,
                    CalendarScreen,
                    FocusScreen,
                    ProfileScreen
                ))
            }
        }
    ) {
        AppNavigation(navController = navController, taskVM = notesVM)
    }

    // Efecto secundario para navegar a la pantalla de inicio de sesión si el usuario no está autenticado
    LaunchedEffect(Unit) {
        if (!isAuthenticated) {
            navController.navigate(AppScreens.SignIn.route) {
                // Asegúrate de que no podemos volver a la pantalla de login una vez autenticados
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Evita crear una nueva instancia si ya estamos viendo la pantalla de inicio de sesión
                launchSingleTop = true
                // Restaura el estado cuando volvamos a esta pantalla
                restoreState = true
            }
        }
    }
}