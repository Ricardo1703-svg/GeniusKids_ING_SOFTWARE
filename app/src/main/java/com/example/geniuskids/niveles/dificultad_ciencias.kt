package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Intermedio.MainActivityInterCiencias
import com.example.geniuskids.Materias.MainActivityCiencias
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.Perfil
import com.example.geniuskids.R

class dificultad_ciencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad_ciencias)


        val Basico = findViewById<Button>(R.id.idBasicoF)
        val Intermedio = findViewById<Button>(R.id.idIntermedioF)
        val Avanzado = findViewById<Button>(R.id.idAvanzadoF)

        Basico.setOnClickListener {
            val intent = Intent(this, MainActivityCiencias::class.java)
            startActivity(intent)
        }

        Intermedio.setOnClickListener {
            val intent = Intent(this, MainActivityInterCiencias::class.java)
            startActivity(intent)
        }


        //-----------------------------Barra de Navegacion------------------------------------------
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