package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_ciencias
import com.example.geniuskids.niveles.dificultad_sociales
import com.example.geniuskids.videos.WebViewTosSociales

class MainActivitySociales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sociales)

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar7)
        btnregresar.setOnClickListener {
            intent = Intent(this, dificultad_sociales::class.java)
            startActivity(intent)
        }

        //----------------------------Barra de Contenidos-------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnCon1Soc)
        val contenido2 = findViewById<Button>(R.id.btnCon2Soc)
        val contenido3 = findViewById<Button>(R.id.btnCon3Soc)
        val contenido4 = findViewById<Button>(R.id.btnCon4Soc)
        //------------------------------------------------------------------------------------------

        //----------------------------Barra de Evaluaciones-----------------------------------------
        val evaluacion1 = findViewById<Button>(R.id.btnEva1Soc)
        val evaluacion3 = findViewById<Button>(R.id.btnEva3Soc)
        val evaluacion4 = findViewById<Button>(R.id.btnEva4Soc)
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Contenidos---------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        contenido4.setOnClickListener {mostrarVideo(4)}
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Evaluaciones-------------------------------------
        evaluacion1.setOnClickListener {mostrarVideo(5)}
        evaluacion3.setOnClickListener {mostrarVideo(6)}
        evaluacion4.setOnClickListener {mostrarVideo(7)}
    }

    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTosSociales::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }
    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/67218f8be991dd2db94d5acd\n\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/672ff9b3f1245ddb8580ab96\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/673001b1ed29137e727a7a22\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6731434f3b08cc3462d83fbc\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //--------------------------------------------------------------------------------------------------

            //----------------------------Evaluaciones----------------------------------------------------------
            5 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/673380b2b7d8d5ca2aae15f7\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            6 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6733cfc30e5a6737f17d27df\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            7 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6733f7261bb6bab862ab0f0c\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //--------------------------------------------------------------------------------------------------
            else -> ""
        }
    }
    override fun onBackPressed() {
    }
}