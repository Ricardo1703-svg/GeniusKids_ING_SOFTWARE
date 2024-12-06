package com.example.geniuskids.Materias.Sociales

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
import com.example.geniuskids.niveles.dificultad_sociales
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Avanzado : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var layoutVideos: LinearLayout
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avanzado_sociales)

        regre = findViewById(R.id.btnregresar6)
        regre.setOnClickListener {
            val intent = Intent(this, dificultad_sociales::class.java)
            startActivity(intent)
        }

        layoutVideos = findViewById(R.id.layoutVideos)
        cargarVideos("Avanzado", "Sociales")
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
            val rowView = LayoutInflater.from(this).inflate(R.layout.row_buttons_sociales, layoutVideos, false)

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
        val intent = Intent(this, WebviewTosAvanSociales::class.java)
        intent.putExtra("VIDEO_URL", videoUrl)
        startActivity(intent)
    }
    override fun onBackPressed() {
    }
}