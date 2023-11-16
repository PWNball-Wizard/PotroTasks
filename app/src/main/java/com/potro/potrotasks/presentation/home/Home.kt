package com.potro.potrotasks.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.potro.potrotasks.R
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.domain.model.TasksViewModel
import com.potro.potrotasks.navigation.AppNavigation
import com.potro.potrotasks.navigation.AppScreens
import com.potro.potrotasks.navigation.AppScreens.*
import com.potro.potrotasks.presentation.task.TaskList
import com.potro.potrotasks.presentation.task.TaskViewModel
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun HomeActivity(navController: NavController, taskVM: TaskViewModel) {
    val context = LocalContext.current
    val tasksState = taskVM.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.Pantalla1),
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White, /*Aquí se establece el color de fondo de la TopAppBar*/)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = DarkGray,
                modifier = Modifier.padding(0.dp, 70.dp),
                onClick = {
                    navController.navigate(AppScreens.AddTask.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar tarea", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el título y la lista

            // Lista de tareas
            LazyColumn(userScrollEnabled = true) {
                items(tasksState.value) { task ->
                    TaskItem(
                        task = task,
                        onClick = {
                            //navController.navigate(AppScreens.TaskDetail.route + "/${task.id}")
                            Toast.makeText(context, "Tarea: ${task.taskName}", Toast.LENGTH_SHORT).show()
                        },
                        onTaskCheckedChange = { task ->
                            taskVM.updateTask(task.copy(taskIsFinished = !task.taskIsFinished))
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp)) // Ajusta la altura según sea necesario
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit, onTaskCheckedChange: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.taskIsFinished,
                onCheckedChange = { isChecked ->
                    onTaskCheckedChange(task)
                }
            )
            Spacer(Modifier.width(8.dp)) // Añade un espacio entre el Checkbox y el texto
            Column {
                Text(text = task.taskName, fontWeight = FontWeight.Bold)
                Text(text = "Categoría: ${task.category}")
                Text(text = "Fecha: ${task.taskDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")
                Text(text = "Hora: ${task.taskTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
                Text(text = "Estado: ${if (task.taskIsFinished) "Finalizada" else "Pendiente"}")
            }
        }
    }
}