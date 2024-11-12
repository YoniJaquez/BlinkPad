package com.nocountry.blinkpad.data.repository

import java.text.SimpleDateFormat
import java.util.Date

data class DataApunte(
    val title: String,
    val text: String,
    val date: String? = obtenerFechaDelApunte(),
    val attachments: List<String>? = null, // Lista de archivos adjuntos
    val contacts: List<String>? = null, // Lista de contactos referenciados
    val scheduledDate: String? = null // Fecha agendada en el calendario (puede ser nullable si no siempre está presente)
)

fun obtenerFechaDelApunte(): String {
    val fecha = Date()
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val fechaActual = formatoFecha.format(fecha)
    return fechaActual
}
