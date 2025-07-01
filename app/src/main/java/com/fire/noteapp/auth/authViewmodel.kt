package com.fire.noteapp.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewmodel: ViewModel() {
    val auth = FirebaseAuth.getInstance()


    fun login(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {


        auth.signInWithEmailAndPassword("email", "password").addOnCompleteListener {
            onResult(it.isSuccessful)
        }
    }


    fun  signup(email: String, password: String, onResult: (Boolean) -> Unit){
        auth.createUserWithEmailAndPassword("email", "password").addOnCompleteListener {
            onResult(it.isSuccessful)
        }
    }


}