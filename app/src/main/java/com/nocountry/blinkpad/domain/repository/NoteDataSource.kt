package com.nocountry.blinkpad.domain.repository

import com.nocountry.blinkpad.domain.model.Note
import com.nocountry.blinkpad.domain.model.Response
import java.io.File

interface NoteDataSource {
    fun getNoteByMateriaId(idMateria: String): List<Note>
    suspend fun createNote(apunte: Note, files: List<File>): Response<Boolean>
    fun updateNote(apunte: Note, files: List<File?>): Response<Boolean>
    fun deleteNote(idApunte: String): Response<Boolean>
}