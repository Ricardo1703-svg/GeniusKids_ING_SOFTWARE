package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Formulario
import com.example.geniuskids.Perfil
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_ciencias
import com.example.geniuskids.niveles.dificultad_lenguaje
import com.example.geniuskids.niveles.dificultad_matematicas
import com.example.geniuskids.niveles.dificultad_sociales
import com.google.android.material.bottomnavigation.BottomNavigationView

class Materias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        Barra()

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
            val intent = Intent(this, dificultad_sociales::class.java)
            startActivity(intent)
        }

        val btnAyuda = findViewById<ImageButton>(R.id.btnAyuda)
        btnAyuda.setOnClickListener {
            mostrarVideo(1)
        }

        val unlockedLevels = intent.getStringArrayListExtra("unlockedLevels")
        unlockedLevels?.let {
            // Usa los niveles desbloqueados para habilitar botones o mostrar contenido
            Log.d("Materias", "Niveles desbloqueados: $unlockedLevels")
        }

    }
    fun Barra(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                /** Igual Aqui ATT: El Tipo
                R.id.nav_logo -> {
                    startActivity(Intent(this, Formulario::class.java))
                    true
                }
                 **/

                R.id.nav_profile -> {
                    startActivity(Intent(this, Perfil::class.java))
                    true
                }
                else -> false
            }
        }
    }
    override fun onBackPressed() {
    }

    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, Ayuda_Materia::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }
    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "https://view.genially.com/67531e59206fc9895595efcb"

            //--------------------------------------------------------------------------------------------------
            else -> ""
        }
    }
}