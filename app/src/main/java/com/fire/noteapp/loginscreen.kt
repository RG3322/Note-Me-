package com.fire.noteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun loginscreen (modifier: Modifier, viewModel: FirebaseAuth, navController: NavController){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)){
        TextField(value = email,onValueChange = {email=it}, label = { Text("Email") })

        TextField(value = password,onValueChange = {password=it}, label = { Text("Password") })


        Button(onClick = {
            // TODO: Add login logic here if needed, or navigate directly
            // For now, let's assume successful login and navigate
            // Replace "next_page_route" with the actual route of your next page
            navController.navigate("next_page_route")
        }) { Text("Go to Next Page") }


        }

}





