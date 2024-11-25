package com.example.geniuskids.Base_de_Datos

data class Video(
    val videoName: String = "",
    val videoUrl: String = "",
    val category: String = "", // contenido o actividad
    val materia: String = "", // sociales, lenguaje, matemática, ciencias
    val dificultad: String = "" // fácil, intermedio, avanzado
)