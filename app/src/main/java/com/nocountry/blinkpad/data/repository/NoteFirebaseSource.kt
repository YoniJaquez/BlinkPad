package com.nocountry.blinkpad.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.nocountry.blinkpad.core.Constants
import com.nocountry.blinkpad.domain.model.Note
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.repository.NoteDataSource
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class NoteFirebaseSource @Inject constructor(
    @Named(Constants.NOTES) private val notesRefs: CollectionReference,
    @Named(Constants.USERS) private val usersRef: CollectionReference,
    @Named(Constants.NOTES) private val storageNotesRef: StorageReference
): NoteDataSource{
    override fun getNoteByMateriaId(idMateria: String): List<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun createNote(apunte: Note, files: List<File>): Response<Boolean> {
        return try {
            val urls : MutableList<String> = mutableListOf()
            files.forEach{file ->
                val fromFile = Uri.fromFile(file)
                val fileRef = storageNotesRef.child("${apunte.title}/${file.name}")
                fileRef.putFile(fromFile).await()
                val url = fileRef.downloadUrl.await()
                urls.add(url.toString())
            }

            apunte.files = urls.toList()
            notesRefs.add(apunte).await()
            Response.Successs(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun updateNote(apunte: Note, files: List<File?>): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override fun deleteNote(idApunte: String): Response<Boolean> {
        TODO("Not yet implemented")
    }
}