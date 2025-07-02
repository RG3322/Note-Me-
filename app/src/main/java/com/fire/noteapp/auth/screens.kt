package com.fire.noteapp.auth

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fire.noteapp.auth.Note
import coil.compose.AsyncImage


@Composable
fun NoteScreen(modifier: Modifier, viewModel: NoteViewModel) {

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }



    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.addNote(Note(title = title, description= description), uri){
            //navController.navigate(Screen.Home.route)
            title="";description=""


            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
        }

    }
    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Add Image")
        }
        LazyColumn {
            items(viewModel.notes.size) {
                index->
                Card(modifier = Modifier.padding(8.dp)) {

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(viewModel.notes[index].title, fontWeight = FontWeight.Bold)
                        Text(viewModel.notes[index].description)
                        Spacer(modifier = Modifier.height(8.dp))

                        if (viewModel.notes[index].imageUrl.isNotEmpty()) {

                            AsyncImage(
                                model = viewModel.notes[index].imageUrl,
                                contentDescription = "Note Image"
                            )

                        }
                    }

                }
            }
        }

    }


}
