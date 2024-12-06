package com.example.geniuskids.Base_de_Datos

data class Video(
    val videoName: String = "",      // Nombre del video
    val videoUrl: String = "",       // URL del video
    val category: String = "",       // contenido o actividad
    val materia: String = "",        // sociales, lenguaje, matemática, ciencias
    val dificultad: String = "",     // fácil, intermedio, avanzado
    val orderNumber: Int = 1         // Número del 1 al 5 para ordenar los botones
)