package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Perfil
import com.example.geniuskids.R
import com.example.geniuskids.videos.WebViewTosCiencias

class MainActivityCiencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ciencias)

        //----------------------------Barra de Botones----------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnIrConte_uno_ciencias)
        val contenido2 = findViewById<Button>(R.id.btnIrConte_dos_ciencias)
        val contenido3 = findViewById<Button>(R.id.btnIrConte_tres_ciencias)
        val contenido4 = findViewById<Button>(R.id.btnIrConte_cuatro_ciencias)
        val contenido5 = findViewById<Button>(R.id.btnIrConte_cinco_ciencias)
        val contenido6 = findViewById<Button>(R.id.btnIrConte_seis_ciencias)
        val contenido7 = findViewById<Button>(R.id.btnIrConte_siete_ciencias)
        val contenido8 = findViewById<Button>(R.id.btnIrConte_ocho_ciencias)

        //----------------------------Seleccion de Videos-------------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        contenido4.setOnClickListener {mostrarVideo(4)}
        //------------------------------------------------------------------------------------------

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
        //------------------------------------------------------------------------------------------
    }

    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTosCiencias::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }
    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cb94d60761e5e5939297b5\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cb96300761e5e59393ee3d\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cbbb900761e5e593ae1531\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cbfafeae7f489d7afc540e\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            else -> ""
        }
    }
}