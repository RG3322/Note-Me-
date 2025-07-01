package com.fire.noteapp.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class NoteViewModel: ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()


    var note = mutableListOf<Note>()
    fun addNote(note: Note, imageUri: Uri?, onComplete: () -> Unit) {
        if (imageUri != null) {
            val imageRef = storage.reference.child("images/${UUID.randomUUID()}")


            val ref = storage.reference.child("images/${UUID.randomUUID()}")

            ref.putFile(imageUri).addOnCompleteListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    val updatedNote = note.copy(imageUrl = uri.toString())
                    saveToFirestoreAndRealTime(updatedNote, onComplete)


                }
            }
        }
        else{
            saveToFirestoreAndRealTime(note, onComplete)
        }
    }


    fun saveToFirestoreAndRealTime(note: Note, onComplete: () -> Unit) {
        firestore.collection("notes").add(note).addOnSuccessListener { doc ->
            val noteId = doc.id

            val pushedNote = note.copy(id = doc.id)

            firebaseDatabase.reference.child("notes").child(noteId).setValue(pushedNote)
            onComplete()


        }
    }
}



