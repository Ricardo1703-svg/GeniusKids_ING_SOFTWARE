package com.example.geniuskids.Intermedio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_sociales
import com.example.geniuskids.videos.WebViewTosSocIntermedio

class MainActivityInterSociales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inter_sociales)

        val btnregresa9 = findViewById<ImageButton>(R.id.btnregresa9)
        btnregresa9.setOnClickListener {
            intent = Intent(this, dificultad_sociales::class.java)
            startActivity(intent)
        }

        //----------------------------Barra de Contenidos-------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnCon1Soc)
        val contenido2 = findViewById<Button>(R.id.btnCon2Soc)
        val contenido3 = findViewById<Button>(R.id.btnCon3Soc)
        //------------------------------------------------------------------------------------------

        //----------------------------Barra de Evaluaciones-----------------------------------------
        val evaluacion1 = findViewById<Button>(R.id.btnEva1Soc)
        val evaluacion2 = findViewById<Button>(R.id.btnEva2Soc)
        val evaluacion3 = findViewById<Button>(R.id.btnEva3Soc)
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Contenidos---------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Evaluaciones-------------------------------------
        evaluacion1.setOnClickListener {mostrarVideo(4)}
        evaluacion2.setOnClickListener {mostrarVideo(5)}
        evaluacion3.setOnClickListener {mostrarVideo(6)}
        //------------------------------------------------------------------------------------------
    }
    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTosSocIntermedio::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }
    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6733646e88350a2a3f582d2f\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6733a80f0e5a6737f15bdae1\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/6734080116580c329a1db05d\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //--------------------------------------------------------------------------------------------------

            //----------------------------Evaluaciones----------------------------------------------------------
            4 -> "<iframe width=\"100%\" height=\"100%\" src=\"------------------------\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            5 -> "<iframe width=\"100%\" height=\"100%\" src=\"------------------------\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            6 -> "<iframe width=\"100%\" height=\"100%\" src=\"------------------------\"" +
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