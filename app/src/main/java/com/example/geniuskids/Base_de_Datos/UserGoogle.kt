package com.example.geniuskids.Base_de_Datos

data class UserGoogle(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val progress: Progress = Progress()
)

data class Progress(
    val Matemática: Int = 0,
    val Sociales: Int = 0,
    val Ciencia: Int = 0,
    val Lenguaje: Int = 0
)

data class DifficultyProgress(
    val Facil: Int = 0,
    val Intermedio: Int = 0,
    val Dificil: Int = 0
)