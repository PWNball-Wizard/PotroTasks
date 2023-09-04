package com.potro.potrotasks.presentation.task

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.navigation.AppScreens
import me.saket.swipe.SwipeAction
import com.potro.potrotasks.R
import me.saket.swipe.SwipeableActionsBox

@SuppressLint("UnrememberedMutableState")
@Composable
fun TaskList(
    currDateTasks : String = "",
    displayFinishedTask : Boolean = false,
    navController: NavController,
    taskListViewModel: TaskListViewModel = viewModel(modelClass = TaskListViewModel::class.java),
){

    LazyColumn(
        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)){

        itemsIndexed(
            items =
            if(currDateTasks != "") taskListViewModel.getCurrentDateTask(currDateTasks)
            else if(displayFinishedTask) taskListViewModel.getFinishedTasks()
            else taskListViewModel.getUnfinishedTasks(),
            itemContent = {_, item->
                AnimatedVisibility(
                    visible = !taskListViewModel.deletedTasks.contains(item.taskID),
                    enter = slideInHorizontally() + fadeIn(),
                    exit = slideOutHorizontally() + fadeOut()
                ) {
                    if(item.taskID != ""){
                        TaskCard(
                            task = item,
                            navController = navController,
                            onDeleteTask = taskListViewModel::deleteTask,
                            onFinishTask = taskListViewModel::finishTask
                        )
                    }
                }
            }
        )
    }

}

@Composable
fun TaskCard(
    task : Task,
    navController: NavController,
    onDeleteTask: (String) -> Unit,
    onFinishTask: (String, Boolean) -> Unit
){

    val params = ("?taskId=${task.taskID}")

    val editTask = SwipeAction(
        onSwipe = {
            navController.navigate(AppScreens.AddEditTaskScreen.route + params + "&onEditTask=true")
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.calendar/*edit_icon*/),//TODO: edit_icon
                contentDescription = null)
        },
        background = Color(0XFFFFF9C5)
    )

    val deleteTask = SwipeAction(
        onSwipe = {
            task.taskID?.let { onDeleteTask(it) }
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.focus/*delete_icon*/),//TODO: delete_icon
                contentDescription = null)
        },
        background = Color(0XFFFF9595)
    )


    SwipeableActionsBox(
        modifier = Modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
            .clickable(onClick = {
                navController.navigate(AppScreens.AddEditTaskScreen.route + params + "&onEditTask=false")
            }),
        swipeThreshold = 100.dp,
        startActions = listOf(editTask),
        endActions = listOf(deleteTask)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(Color.White)
                .padding(PaddingValues(start = 20.dp, end = 10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val taskId = task.taskID
            val isFinished = task.taskIsFinished

            if (isFinished != null) {
                Checkbox(
                    checked = isFinished,
                    onCheckedChange = {
                        if (taskId != null)
                            onFinishTask(taskId, it)
                    },
                    colors = CheckboxDefaults.colors(Color.Black)
                )
            }

            Column(
                modifier = Modifier.padding(PaddingValues(start = 20.dp)),
                horizontalAlignment = Alignment.Start
            ) {

                task.taskName?.let {
                    Text(
                        text = it,
                        fontFamily = interMedium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        textDecoration =
                        if(task.taskIsFinished == true) TextDecoration.LineThrough
                        else TextDecoration.None
                    )
                }

                task.taskDueDate?.let {
                    Text(
                        text = if (it != "No due date") "Due on $it" else "No due date",
                        fontFamily = interLight,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}


fun <T> SnapshotStateList<T>.updateList(newList: List<T>){
    clear()
    addAll(newList)
}