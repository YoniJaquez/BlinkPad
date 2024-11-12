package com.nocountry.blinkpad.domain.model

data class Note(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var materia: Materia,
    var contacto: String = "",
    var fechaDeCreación: String = "",
    var files: List<String> = listOf(),
    var idMateria: Materia
)
