package com.potro.potrotasks.presentation.screens

import android.content.Context
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.domain.model.TasksViewModel
import com.potro.potrotasks.presentation.task.TaskViewModel
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addTask(navController: NavController, viewModel: TaskViewModel) {
    var taskName by remember { mutableStateOf("") }
    var taskCategory by remember { mutableStateOf("") }
    var taskDate by remember { mutableStateOf(LocalDate.now()) }
    var taskTime by remember { mutableStateOf(LocalTime.now()) }
    val showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Nueva nota")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            contentDescription = "Back",
                            imageVector = Icons.Filled.ArrowBack,
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White, /*Aquí se establece el color de fondo de la TopAppBar*/)
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { androidx.compose.material3.Text(text = "Task Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                )
                OutlinedTextField(
                    value = taskCategory,
                    onValueChange = { taskCategory = it },
                    label = { androidx.compose.material3.Text(text = "Categoría") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                )
                if (showDatePicker.value) {
                    DatePickerDialog(
                        shown = showDatePicker,
                        date = taskDate,
                        onDateSelected = { selectedDate ->
                            taskDate = selectedDate
                            // Puedes necesitar lógica adicional aquí si necesitas hacer algo más con la fecha.
                        }
                    )
                }

                if (showTimePicker.value) {
                    TimePickerDialog(
                        shown = showTimePicker,
                        time = taskTime,
                        onTimeSelected = { selectedTime ->
                            taskTime = selectedTime
                            // Puedes necesitar lógica adicional aquí si necesitas hacer algo más con la hora.
                        }
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Button(onClick = { showDatePicker.value = true }) {
                        Text(text = "Elegir fecha")
                    }
                    Button(onClick = { showTimePicker.value = true }) {
                        Text(text = "Elegir hora")
                    }
                }

                // Muestra la fecha y hora seleccionadas
                Text(text = "Fecha seleccionada: ${taskDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")
                Text(text = "Hora seleccionada: ${taskTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")

                Button(onClick = {
                    val newTask = Task(
                        taskName = taskName,
                        category = taskCategory,
                        taskDate = taskDate,
                        taskTime = taskTime
                    )

                    viewModel.insertTask(newTask) // Asegúrate de que tu ViewModel tenga un método insertTask que inserte la tarea usando tu DAO

                    Toast.makeText(context, "Tarea añadida con éxito!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()

                }) {
                    Text(text = "Agregar Tarea")
                }
            }
        }
    }
}

@Composable
fun DatePickerDialog(
    shown: MutableState<Boolean>,
    date: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    if (shown.value) {
        AlertDialog(
            onDismissRequest = {
                shown.value = false
            },
            title = {
                Text(text = "Choose Date")
            },
            text = {
                val context = LocalContext.current
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { ctx ->
                        DatePicker(ctx).apply {
                            // Iniciar el DatePicker con la fecha actual o seleccionada
                            init(date.year, date.monthValue - 1, date.dayOfMonth) { _, year, monthOfYear, dayOfMonth ->
                                // Actualizar la fecha cuando se selecciona una nueva
                                onDateSelected(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
                            }
                        }
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        shown.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        shown.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Composable
fun TimePickerDialog(
    shown: MutableState<Boolean>,
    time: LocalTime,
    onTimeSelected: (LocalTime) -> Unit
) {
    if (shown.value) {
        AlertDialog(
            onDismissRequest = { shown.value = false },
            title = { Text(text = "Choose Time") },
            text = {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        TimePicker(context).apply {
                            setIs24HourView(android.text.format.DateFormat.is24HourFormat(context))
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                hour = time.hour
                                minute = time.minute
                            } else {
                                @Suppress("DEPRECATION")
                                currentHour = time.hour
                                @Suppress("DEPRECATION")
                                currentMinute = time.minute
                            }
                            setOnTimeChangedListener { _, hourOfDay, minuteOfHour ->
                                // Esta línea será ejecutada cada vez que el usuario cambie la hora en el TimePicker
                                onTimeSelected(LocalTime.of(hourOfDay, minuteOfHour))
                            }
                        }
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = { shown.value = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { shown.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}