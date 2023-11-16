package com.potro.potrotasks.domain.model

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TasksViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    fun signOut(){
        auth.signOut()
    }

    fun getCurrentUser() = auth.currentUser

    fun saveTask(){
        val db = Firebase.firestore
        val user = auth.currentUser
        if (user != null) {
            //db.collection("users").document(user.uid).collection("tasks").document(task.taskID).set(task)
        }
    }

    fun resetState(){
        //deletedTasks.clear()
    }

}