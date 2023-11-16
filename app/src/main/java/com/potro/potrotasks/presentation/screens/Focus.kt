package com.potro.potrotasks.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//importamos otras variables de mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.potro.potrotasks.R
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun FocusScreen(navController: NavController) {
    /*TopBar(navController)

    BottomNavigationMenu(navController)*/

    // Estados para el temporizador
    var timeLeft by remember { mutableStateOf(25 * 60 * 1000) } // 25 minutos
    var timerRunning by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current

    // Observar los cambios en el ciclo de vida para pausar el temporizador cuando la app esté en background
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                timerRunning = false
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Lógica del temporizador
    LaunchedEffect(timerRunning) {
        while (timerRunning && timeLeft > 0) {
            delay(1000)
            timeLeft -= 1000
        }

        if (timeLeft <= 0) {
            timerRunning = false
            // Notifica al usuario que el tiempo se ha acabado, puedes vibrar el teléfono o sonar una alarma
        }
    }

    // UI del temporizador
    Column {
        Text(text = "Tiempo restante: ${timeLeft / 60000}:${(timeLeft / 1000) % 60}")

        Button(onClick = { timerRunning = !timerRunning }) {
            Text(text = if (timerRunning) "Pausar" else "Iniciar")
        }

        Button(onClick = {
            timeLeft = 25 * 60 * 1000 // Restablece a 25 minutos
            timerRunning = false
        }) {
            Text(text = "Reiniciar")
        }
    }
}