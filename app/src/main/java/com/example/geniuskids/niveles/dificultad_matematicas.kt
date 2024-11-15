package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.geniuskids.Intermedio.MainActivityInterMatematicas
import com.example.geniuskids.Materias.MainActivityMatematica
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class dificultad_matematicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dificultad_matematicas)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.matematicas)
        lottieAnimationView.playAnimation()

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar4)
        btnregresar.setOnClickListener {
            intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }
        Barra()
    }
    fun Barra(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Establecer el ítem seleccionado en "Home"
        bottomNav.selectedItemId = R.id.nav_facil_mate

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_facil_mate -> {
                    startActivity(Intent(this, MainActivityMatematica::class.java))
                    true
                }
                R.id.nav_intermedio_mate -> {
                    startActivity(Intent(this, MainActivityInterMatematicas::class.java))
                    true
                }
                /**
                R.id.nav_avanzado_mate -> {
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