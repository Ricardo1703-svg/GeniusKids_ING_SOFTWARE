package com.example.geniuskids.Intermedio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.example.geniuskids.niveles.dificultad_matematicas
import com.example.geniuskids.videos.WebViewTosMateIntermedio

class MainActivityInterMatematicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inter_matematicas)

        val btnregresar = findViewById<ImageButton>(R.id.btnregresar5)
        btnregresar.setOnClickListener {
            intent = Intent(this, dificultad_matematicas::class.java)
            startActivity(intent)
        }

        //----------------------------Barra de Contenidos-------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnCon1Mate)
        val contenido2 = findViewById<Button>(R.id.btnCon2Mate)
        val contenido3 = findViewById<Button>(R.id.btnCon3Mate)
        //------------------------------------------------------------------------------------------

        //----------------------------Barra de Evaluaciones-----------------------------------------
        val btnEvaluacion1 = findViewById<Button>(R.id.btnEva1Mate)
        val btnEvaluacion2 = findViewById<Button>(R.id.btnEva2Mate)
        val btnEvaluacion3 = findViewById<Button>(R.id.btnEva3Mate)
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Contenidos---------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Evaluaciones-------------------------------------
        btnEvaluacion1.setOnClickListener {mostrarVideo(4)}
        btnEvaluacion2.setOnClickListener {mostrarVideo(5)}
        btnEvaluacion3.setOnClickListener {mostrarVideo(6)}
        //------------------------------------------------------------------------------------------
    }

    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTosMateIntermedio::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }
    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/670c26d8d0a56c38d4185a22\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/670c5caf844fe3427e9a0bd8\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/670b0fc1353c874ce93cf102\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //------------------------------------------------------------------------------------------------

            //----------------------------Evaluaciones----------------------------------------------------------
            4 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/670c75aa353c874ce90d4004\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            5 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/670c87a07462b0d102fd7378\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            6 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/670c75d5524dfed76e2efaad\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            else -> ""
        }
    }
    override fun onBackPressed() {
    }
}