package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Perfil
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_ciencias
import com.example.geniuskids.niveles.dificultad_lenguaje
import com.example.geniuskids.niveles.dificultad_matematicas


class Materias : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_materias)

        val MatematicaClase = findViewById<Button>(R.id.btnIrMatematicas)
        MatematicaClase.setOnClickListener {
            val intent = Intent(this, dificultad_matematicas::class.java)
            startActivity(intent)
        }

        val CienciasClase = findViewById<Button>(R.id.btnIrCiencia)
        CienciasClase.setOnClickListener {
            val intent = Intent(this, dificultad_ciencias::class.java)
            startActivity(intent)
        }

        val LenguajeClase = findViewById<Button>(R.id.btnIrLenguaje)
        LenguajeClase.setOnClickListener {
            val intent = Intent(this, dificultad_lenguaje::class.java)
            startActivity(intent)
        }

        val SocialesClase = findViewById<Button>(R.id.btnIrSociales)
        SocialesClase.setOnClickListener {
            val intent = Intent(this, dificultad_lenguaje::class.java)
            startActivity(intent)
        }

        //-------------------Barra de Nvegacion------------------------------------------
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
        //-------------------------------------------------------------------------------

    }
}