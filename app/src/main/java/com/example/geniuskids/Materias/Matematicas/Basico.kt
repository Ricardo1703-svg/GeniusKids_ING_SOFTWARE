package com.example.geniuskids.Materias.Matematicas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Video
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_matematicas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Basico : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var layoutVideos: LinearLayout
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_matematica)

        regre = findViewById(R.id.btnregresar6)
        regre.setOnClickListener {
            val intent = Intent(this, dificultad_matematicas::class.java)
            startActivity(intent)
            finish() // Liberar actividad actual
        }

        layoutVideos = findViewById(R.id.layoutVideos)
        cargarVideos("Fácil", "Matemática")
    }

    private fun cargarVideos(dificultad: String, materia: String) {
        db.collection("videos")
            .whereEqualTo("dificultad", dificultad)
            .whereEqualTo("materia", materia)
            .orderBy("orderNumber", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                val contenidos = mutableListOf<Video>()
                val actividades = mutableListOf<Video>()

                for (document in documents) {
                    val video = document.toObject(Video::class.java)
                    when (video.category) {
                        "contenido" -> contenidos.add(video)
                        "actividad" -> actividades.add(video)
                    }
                }

                if (contenidos.isEmpty() && actividades.isEmpty()) {
                    Toast.makeText(this, "No hay videos disponibles", Toast.LENGTH_SHORT).show()
                } else {
                    displayVideos(contenidos, actividades)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar videos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun displayVideos(contenidos: List<Video>, actividades: List<Video>) {
        val maxCount = maxOf(contenidos.size, actividades.size)

        for (i in 0 until maxCount) {
            val rowView = LayoutInflater.from(this).inflate(R.layout.row_buttons_matematicas, layoutVideos, false)

            val btnContenido = rowView.findViewById<Button>(R.id.btnContenido)
            val btnActividad = rowView.findViewById<Button>(R.id.btnActividad)

            // Configurar botón de contenido
            if (i < contenidos.size) {
                val contenido = contenidos[i]
                btnContenido.text = contenido.videoName
                btnContenido.setOnClickListener {
                    openWebView(contenido.videoUrl)
                }
            } else {
                btnContenido.visibility = Button.GONE
            }

            // Configurar botón de actividad
            if (i < actividades.size) {
                val actividad = actividades[i]
                btnActividad.text = actividad.videoName
                btnActividad.setOnClickListener {
                    openWebView(actividad.videoUrl)
                }
            } else {
                btnActividad.visibility = Button.GONE
            }

            layoutVideos.addView(rowView)
        }
    }

    private fun openWebView(videoUrl: String) {
        val intent = Intent(this, WebViewTos::class.java)
        intent.putExtra("VIDEO_URL", videoUrl)

        updateProgress("Matemática", "Facil", 1)
        startActivity(intent)
    }

    private fun updateProgress(materia: String, dificultad: String, increment: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val userDocRef = db.collection("user_Google").document(userId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(userDocRef)
            val currentProgress = snapshot.getLong("$materia.$dificultad") ?: 0
            val newProgress = currentProgress + increment

            transaction.update(userDocRef, "$materia.$dificultad", newProgress)
        }.addOnSuccessListener {
            Toast.makeText(this, "Progreso actualizado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Error al actualizar progreso: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        // Bloqueo de retroceso para forzar uso del botón "Regresar"
    }
}
