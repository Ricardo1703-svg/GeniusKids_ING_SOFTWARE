package com.example.geniuskids.Materias.Ciencias

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
import com.example.geniuskids.niveles.dificultad_ciencias
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Intermedio : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var layoutVideos: LinearLayout
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inter_ciencias)

        regre = findViewById(R.id.btnregresar)
        regre.setOnClickListener {
            val intent = Intent(this, dificultad_ciencias::class.java)
            startActivity(intent)
        }

        layoutVideos = findViewById(R.id.layoutVideos)
        verificarProgresoFacil()
    }

    private fun verificarProgresoFacil() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val userDocRef = db.collection("user_Google").document(userId)

        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val progressFacil = document.getLong("Ciencias.Facil") ?: 0
                    val totalVideosFacil = 8

                    if (progressFacil >= totalVideosFacil) {
                        cargarVideos("Intermedio", "Ciencias")
                    } else {
                        mostrarBloqueoAcceso() // Nueva función para manejar este caso
                    }
                } else {
                    mostrarBloqueoAcceso()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al verificar el progreso: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    private fun mostrarBloqueoAcceso() {
        Toast.makeText(this, "Debes completar la dificultad 'Fácil' antes de acceder", Toast.LENGTH_SHORT).show()
        layoutVideos.removeAllViews() // Asegurarse de no mostrar contenido
        // Podrías mostrar un mensaje visual en el diseño
    }



    private fun cargarVideos(dificultad: String, materia: String) {
        db.collection("videos")
            .whereEqualTo("dificultad", dificultad)
            .whereEqualTo("materia", materia)
            .orderBy("orderNumber", Query.Direction.ASCENDING) // Ordenar por el número asignado
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
            val rowView = LayoutInflater.from(this).inflate(R.layout.row_buttons, layoutVideos, false)

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
        val intent = Intent(this, WebViewTosCienciasIntermedio::class.java)
        intent.putExtra("VIDEO_URL", videoUrl)
        startActivity(intent)
    }
    override fun onBackPressed() {
    }
}