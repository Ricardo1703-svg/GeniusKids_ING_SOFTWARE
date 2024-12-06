package com.example.geniuskids.niveles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.geniuskids.Materias.Lenguaje.Basico
import com.example.geniuskids.Materias.Lenguaje.Intermedio
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class dificultad_lenguaje : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var btnFacil: Button
    private lateinit var btnIntermedio: Button
    private lateinit var btnDificil: Button
    private lateinit var btnRegresar: ImageButton
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad_lenguaje)

        // Configuración de animación
        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.lenguaje)
        lottieAnimationView.playAnimation()

        // Botón para regresar a "Materias"
        btnRegresar = findViewById<ImageButton>(R.id.btnregresar11)
        btnRegresar.setOnClickListener {
            val intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        // Configuración de botones
        configurarBotones()

        // Verifica el acceso a la dificultad "Intermedio"
        verificarProgresoParaIntermedio()
    }

    private fun configurarBotones() {
        btnFacil = findViewById(R.id.btnFacil)
        btnIntermedio = findViewById(R.id.btnIntermedio)
        btnDificil = findViewById(R.id.btnDificil)

        // Deshabilitar el botón de Intermedio por defecto
        btnIntermedio.isEnabled = true

        // Acción del botón Fácil
        btnFacil.setOnClickListener {
            val intent = Intent(this, Basico::class.java)
            startActivity(intent)
        }

        // Acción del botón Intermedio
        btnIntermedio.setOnClickListener {
            if (btnIntermedio.isEnabled) {
                val intent = Intent(this, Intermedio::class.java)
                startActivity(intent)
            } else {
                // Mostrar mensaje si no se ha desbloqueado la dificultad
                Toast.makeText(this, "Debes completar todos los videos de la dificultad 'Fácil' primero", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción del botón Difícil (puedes configurar esto en el futuro)
        btnDificil.setOnClickListener {
            Toast.makeText(this, "Esta dificultad aún no está disponible", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verificarProgresoParaIntermedio() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        db.collection("user_Google").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val progresoFacil = document.getLong("Lenguaje.Facil") ?: 0
                    val totalVideosFacil = 5 // Número total de videos/actividades en "Fácil"

                    if (progresoFacil >= totalVideosFacil) {
                        // Habilitar el botón Intermedio si el progreso es suficiente
                        btnIntermedio.isEnabled = true
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al verificar el progreso: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBackPressed() {
        // No permitir retroceder
    }
}
