package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.geniuskids.Materias.MainActivityLenguaje
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class dificultad_lenguaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad_lenguaje)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.lenguaje)
        lottieAnimationView.playAnimation()

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar11)
        btnregresar.setOnClickListener {
            intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        Barra()
    }

    fun Barra(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_facil_lenguaje -> {
                    startActivity(Intent(this, MainActivityLenguaje::class.java))
                    true
                }
                R.id.nav_intermedio_lenguaje -> {

                    //startActivity(Intent(this, MainActivityInterLenguaje::class.java))
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