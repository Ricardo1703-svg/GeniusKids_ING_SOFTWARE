package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Perfil
import com.example.geniuskids.R
import com.example.geniuskids.videos.WebViewTosLengua

class MainActivityLenguaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lenguaje)

        //----------------------------Barra de Contenidos-------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnCon1Lenguaje)
        val contenido2 = findViewById<Button>(R.id.btnCon2Lenguaje)
        val contenido3 = findViewById<Button>(R.id.btnCon3Lenguaje)
        val contenido4 = findViewById<Button>(R.id.btnCon4Lenguaje)
        //------------------------------------------------------------------------------------------

        //----------------------------Barra de Evaluaciones-----------------------------------------
        val evaluacion1 = findViewById<Button>(R.id.btnEva1Lenguaje)
        val evaluacion2 = findViewById<Button>(R.id.btnEva2Lenguaje)
        val evaluacion3 = findViewById<Button>(R.id.btnEva3Lenguaje)
        val evaluacion4 = findViewById<Button>(R.id.btnEva4Lenguaje)
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Contenidos---------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        contenido4.setOnClickListener {mostrarVideo(4)}
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Evaluaciones-------------------------------------
        evaluacion1.setOnClickListener {mostrarVideo(5)}
        evaluacion2.setOnClickListener {mostrarVideo(6)}
        evaluacion3.setOnClickListener {mostrarVideo(7)}
        evaluacion4.setOnClickListener {mostrarVideo(8)}
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
        val intent = Intent(this, WebViewTosLengua::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }

    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66f3146db963df603c2ecfe2\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66f3197bbd12210d5f440930\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66f61f846ed2f5db32ad1c2a\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 -> "<iframe width=\"100%\" height=\"100%\" src=\"4\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //--------------------------------------------------------------------------------------------------

            //----------------------------Evaluaciones----------------------------------------------------------
            5 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66f61bf7b39d4ffd80ad1219\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            6 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66f4c833d2a395bf2d6c27d5\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            7 -> "<iframe width=\"100%\" height=\"100%\" src=\"3\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            8 -> "<iframe width=\"100%\" height=\"100%\" src=\"4\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //--------------------------------------------------------------------------------------------------
            else -> ""
        }
    }
}