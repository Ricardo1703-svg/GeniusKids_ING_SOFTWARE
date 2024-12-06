package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class dificultad_sociales : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var btnFacil: Button
    private lateinit var btnIntermedio: Button
    private lateinit var btnDificil: Button
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad_sociales)

        // Configuración de animación
        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.sociales)
        lottieAnimationView.playAnimation()

        // Configuración de botones
        configurarBotones()
        verificarProgresoParaIntermedio()

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar10)
        btnregresar.setOnClickListener {
            intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }
    }

    private fun configurarBotones() {
        btnFacil = findViewById(R.id.btnFacil)
        btnIntermedio = findViewById(R.id.btnIntermedio)
        btnDificil = findViewById(R.id.btnDificil)

        // Deshabilitar el botón de Intermedio por defecto
        btnIntermedio.isEnabled = false

        // Acción del botón "Fácil"
        btnFacil.setOnClickListener {
            val intent = Intent(this, com.example.geniuskids.Materias.Sociales.Basico::class.java)
            startActivity(intent)
        }

        // Acción del botón "Intermedio"
        btnIntermedio.setOnClickListener {
            if (btnIntermedio.isEnabled) {
                val intent = Intent(this, com.example.geniuskids.Materias.Sociales.Intermedio::class.java)
                startActivity(intent)
            } else {
                // Mostrar mensaje si no se ha desbloqueado la dificultad
                Toast.makeText(
                    this,
                    "Debes completar todos los videos de la dificultad 'Fácil' primero",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Acción del botón "Difícil"
        btnDificil.setOnClickListener {
            Toast.makeText(this, "Esta dificultad aún no está disponible", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verificarProgresoParaIntermedio() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        db.collection("user_Google").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Obtener el progreso del nivel "Fácil"
                    val progresoFacil = document.getLong("Sociales.Facil") ?: 0

                    // Total de actividades/videos necesarios para completar el nivel "Fácil"
                    val totalVideosFacil = 8

                    if (progresoFacil >= totalVideosFacil) {
                        // Habilitar el botón "Intermedio" si el progreso es suficiente
                        btnIntermedio.isEnabled = true
                    } else {
                        // Mantener el botón deshabilitado y notificar al usuario si intenta acceder
                        btnIntermedio.isEnabled = true
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al verificar el progreso: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBackPressed() {
    }
}