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
import com.example.geniuskids.videos.WebViewTos

class MainActivityMatematica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_matematica)

        val contenido1 = findViewById<Button>(R.id.btnIrConte_uno_mate)
        contenido1.setOnClickListener {mostrarVideo(1)}

        val contenido2 = findViewById<Button>(R.id.btnIrConte_dos_mate)
        contenido2.setOnClickListener {mostrarVideo(2)}

        val contenido3 = findViewById<Button>(R.id.btnIrConte_tres_mate)
        contenido3.setOnClickListener {mostrarVideo(3)}

        val contenido4 = findViewById<Button>(R.id.btnIrConte_cuatro_mate)
        contenido4.setOnClickListener {mostrarVideo(4)}

        val contenido5 = findViewById<Button>(R.id.btnIrConte_cinco_mate)
        contenido5.setOnClickListener {mostrarVideo(5)}

        //----------------------------Barra de Navegacion-------------------------------------------
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
    }
    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTos::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }

    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://ricardo1703-svg.github.io/Repositorios/GeniusKids/principal/Video1.html\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66ca0d9b9ef57cfeeced8b60/interactive-content-juego-prueba\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66ca11b63a149c3c124381e5\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66ca66003a149c3c127b4999\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            5 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66ca92963a149c3c12951f45\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            else -> ""
        }
    }
}