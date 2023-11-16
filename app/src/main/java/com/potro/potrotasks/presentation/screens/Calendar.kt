package com.potro.potrotasks.presentation.screens

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//traer getValue y setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.potro.potrotasks.R
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.presentation.home.TaskItem
import com.potro.potrotasks.presentation.task.TaskViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun CalendarScreen(navController: NavController, taskVM: TaskViewModel) {
    //var selectedDate by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_DATE)) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate = selectedDate.format(DateTimeFormatter.ISO_DATE)
    val tasksByDate = taskVM.getTasksByDate(formattedDate).collectAsState(initial = emptyList())
    //val tasksByDate = taskVM.tasksForDate.collectAsState(initial = emptyList())
    //val tasksByDate = taskVM.getTasksByDate(selectedDate).collectAsState(initial = emptyList())
    // Este efecto se reinicia cada vez que la selectedDate cambia.
    LaunchedEffect(selectedDate) {
        val formattedDate = selectedDate.format(DateTimeFormatter.ISO_DATE)
        taskVM.updateTasksForDate(formattedDate)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text(
                text = stringResource(id = R.string.Pantalla2),
                fontSize = 20.sp,
                color = Color.Black
            )},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White, /*Aquí se establece el color de fondo de la TopAppBar*/)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 0.dp,  // Asegúrate de que no haya padding al inicio
                        end = 0.dp,    // Asegúrate de que no haya padding al final
                        bottom = 0.dp  // Asegúrate de que no haya padding al fondo
                    )
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp),
                    factory = { CalendarView(it) },
                    update = { calendarView ->
                        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                            // Asegúrate de que la asignación de la fecha seleccionada sea correcta
                            // Convertimos year, month, dayOfMonth a LocalDate antes de asignar
                            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                        }
                        /*calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                            // Asegúrate de que la asignación de la fecha seleccionada sea correcta
                            selectedDate = "$dayOfMonth/${month + 1}/$year"
                            // Esta línea es importante para que la UI se actualice con las tareas de la nueva fecha
                        }*/
                    }
                )
                /*Text(
                    text = "Selected Date: $selectedDate",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )*/

                // Lista de tareas para la fecha seleccionada
                LazyColumn(
                    modifier = Modifier.weight(1F)
                ) {
                    items(tasksByDate.value) { task ->
                        TaskItem(
                            task = task,
                            onClick = {
                                // Navegación o acción al hacer clic en la tarea
                            },
                            onTaskCheckedChange = { updatedTask ->
                                // Acción cuando se cambia el estado de la tarea
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp)) // Ajusta la altura según sea necesario
                    }
                }
            }
        }
    )
}