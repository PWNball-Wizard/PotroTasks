package com.potro.potrotasks.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object UserDataSource {

    fun getUserName() : String {
        return Firebase.auth.currentUser?.displayName.toString()
    }

    fun getUserPicture() : String {
        return "${Firebase.auth.currentUser?.photoUrl}?access_token=${AccessToken.getCurrentAccessToken()?.token}"
        /*
        Dino: Supongo que me falta un archivo que tontiene la clase "AccessToken"
         */
    }
}