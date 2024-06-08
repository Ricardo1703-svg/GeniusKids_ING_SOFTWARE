package com.example.geniuskids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Nivel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nivel)

        val Basico = findViewById<Button>(R.id.idBasico)
        val Intermedio = findViewById<Button>(R.id.idmedio)
        val Avanzado = findViewById<Button>(R.id.idAvanzado)

        Basico.setOnClickListener {
            val intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        Intermedio.setOnClickListener {
            Toast.makeText(this, "Intermedio PROXIMAMENTE", Toast.LENGTH_SHORT).show()
        }
        Avanzado.setOnClickListener {
            Toast.makeText(this, "Avanzado PROXIMAMENTE", Toast.LENGTH_SHORT).show()
        }
    }
}