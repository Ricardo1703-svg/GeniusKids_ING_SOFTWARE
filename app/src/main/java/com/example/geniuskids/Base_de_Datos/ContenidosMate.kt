package com.example.geniuskids.Base_de_Datos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.geniuskids.R

class ContenidosMate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contenidos_mate)

        val username = intent.getStringExtra("username")
        val profileImageUrl = intent.getStringExtra("profileImageUrl")

        // Referencia a los elementos del layout
        val usernameTextView: TextView = findViewById(R.id.usernameTextView)
        val profileImageView: ImageView = findViewById(R.id.profileImageView)

        // Configura el nombre de usuario
        usernameTextView.text = username

        // Carga la imagen de perfil usando Glide
        Glide.with(this)
            .load(profileImageUrl)
            .placeholder(R.drawable.logo) // Imagen de espera mientras se carga la imagen real
            .error(R.drawable.logo)       // Imagen de error si no se puede cargar la imagen
            .into(profileImageView)

    }
}