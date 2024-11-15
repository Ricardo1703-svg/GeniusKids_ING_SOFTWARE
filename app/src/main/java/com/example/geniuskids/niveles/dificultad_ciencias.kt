package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.geniuskids.Intermedio.MainActivityInterCiencias
import com.example.geniuskids.Materias.MainActivityCiencias
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class dificultad_ciencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad_ciencias)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.ciencias)
        lottieAnimationView.playAnimation()

        Barra()

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar3)
        btnregresar.setOnClickListener {
            intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }
    }
    fun Barra(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Establecer el ítem seleccionado en "Home"
        bottomNav.selectedItemId = R.id.nav_facil_ciencias

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_facil_ciencias -> {
                    startActivity(Intent(this, MainActivityCiencias::class.java))
                    true
                }
                R.id.nav_intermedio_ciencias -> {
                    startActivity(Intent(this, MainActivityInterCiencias::class.java))
                    true
                }
                /**
                R.id.nav_avanzado_ciencias -> {
                    startActivity(Intent(this, Niveles::class.java))
                    true
                }
                **/
                else -> false
            }
        }
    }
    override fun onBackPressed() {
    }
}