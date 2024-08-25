package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Materias.MainActivityMatematica
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.Perfil
import com.example.geniuskids.R

class Nivel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nivel)

        val materia = intent.getStringExtra("MATERIA")
        val textViewMateria = findViewById<TextView>(R.id.textViewMateriaF)
        textViewMateria.text = materia

        val Basico = findViewById<Button>(R.id.idBasico)
        val Intermedio = findViewById<Button>(R.id.idmedio)
        val Avanzado = findViewById<Button>(R.id.idAvanzado)

        Basico.setOnClickListener {
            val intent = Intent(this, MainActivityMatematica::class.java)
            startActivity(intent)
        }

        Intermedio.setOnClickListener {
            Toast.makeText(this, "Intermedio PROXIMAMENTE", Toast.LENGTH_SHORT).show()
        }
        Avanzado.setOnClickListener {
            Toast.makeText(this, "Avanzado PROXIMAMENTE", Toast.LENGTH_SHORT).show()
        }

        //Barra de Nvegacion
        val Home = findViewById<ImageButton>(R.id.btnHome)
        Home.setOnClickListener {
            val intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val btnMaterias = findViewById<ImageButton>(R.id.btnMaterias)
        btnMaterias.setOnClickListener{
            val intent = Intent(this, Ingresar_Datos::class.java)
            startActivity(intent)
        }
    }
}