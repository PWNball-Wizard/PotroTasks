package com.potro.potrotasks.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.presentation.task.updateList

object TaskDataSource {

    private const val USER_COLLECTION = "users"
    private const val TASK_COLLECTION = "tasks"

    fun getTasks(tasks: SnapshotStateList<Task>)  {
        FirebaseFirestore
            .getInstance()
            .collection(USER_COLLECTION)
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection(TASK_COLLECTION)
            .addSnapshotListener { snapshot, e ->
                if(snapshot != null){
                    tasks.updateList(snapshot.toObjects(Task::class.java))
                }
            }
    }

    fun getTask(
        taskId: String,
        onError : (Throwable?) -> Unit,
        onSuccess : (Task?) -> Unit
    ) {
        FirebaseFirestore
            .getInstance()
            .collection(USER_COLLECTION)
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection(TASK_COLLECTION)
            .document(taskId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Task::class.java))
            }
            .addOnFailureListener {result ->
                onError.invoke(result.cause)
            }
    }

    fun saveTask(
        taskName: String,
        taskDesc: String,
        taskDueDate: String,
        taskIsFinished: Boolean,
        taskId: String,
    ){
        val task = Task (
            taskName = taskName,
            taskDescription = taskDesc,
            taskDueDate = taskDueDate,
            taskIsFinished = taskIsFinished,
            taskID = if(taskId == "") generateTaskID() else taskId
        )

        task.taskID?.let {
            FirebaseFirestore
                .getInstance()
                .collection(USER_COLLECTION)
                .document(Firebase.auth.currentUser?.uid.toString())
                .collection(TASK_COLLECTION)
                .document(it)
                .set(task)
        }
    }

    fun deleteTask(taskID : String) {
        FirebaseFirestore
            .getInstance()
            .collection(USER_COLLECTION)
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection(TASK_COLLECTION)
            .document(taskID)
            .delete()
    }

    fun finishTask(taskId : String, isFinished : Boolean) {
        FirebaseFirestore
            .getInstance()
            .collection(USER_COLLECTION)
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection(TASK_COLLECTION)
            .document(taskId)
            .update("taskIsFinished", isFinished)
    }

    private fun generateTaskID(): String {
        val ref = Firebase.firestore
            .collection(USER_COLLECTION)
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection(TASK_COLLECTION)
            .document()

        return ref.id
    }
}