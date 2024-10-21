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

        //----------------------------Barra de Contenidos-------------------------------------------
        val contenido1 = findViewById<Button>(R.id.btnCon1Mate)
        val contenido2 = findViewById<Button>(R.id.btnCon2Mate)
        val contenido3 = findViewById<Button>(R.id.btnCon3Mate)
        val contenido4 = findViewById<Button>(R.id.btnCon4Mate)
        //------------------------------------------------------------------------------------------

        //----------------------------Barra de Evaluaciones-----------------------------------------
        val btnEvaluacion1 = findViewById<Button>(R.id.btnEva1Mate)
        val btnEvaluacion2 = findViewById<Button>(R.id.btnEva2Mate)
        val btnEvaluacion3 = findViewById<Button>(R.id.btnEva3Mate)
        val btnEvaluacion4 = findViewById<Button>(R.id.btnEva4Mate)
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Contenidos---------------------------------------
        contenido1.setOnClickListener {mostrarVideo(1)}
        contenido2.setOnClickListener {mostrarVideo(2)}
        contenido3.setOnClickListener {mostrarVideo(3)}
        contenido4.setOnClickListener {mostrarVideo(4)}
        //------------------------------------------------------------------------------------------

        //----------------------------Seleccion de Evaluaciones-------------------------------------
        btnEvaluacion1.setOnClickListener {mostrarVideo(5)}
        btnEvaluacion2.setOnClickListener {mostrarVideo(6)}
        btnEvaluacion3.setOnClickListener {mostrarVideo(7)}
        btnEvaluacion4.setOnClickListener {mostrarVideo(8)}
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
        val intent = Intent(this, WebViewTos::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }

    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            //----------------------------Contenidos----------------------------------------------------------
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66ce1cf726ffb885ed676e34\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cbd36b0761e5e593bf1cfd\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://view.genially.com/66cbe4c032abdd7bf40bc877\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66d24a3f5d8332a6dd19cf9f\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            //------------------------------------------------------------------------------------------------

            //----------------------------Evaluaciones----------------------------------------------------------
            5 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66d10127fc3e22ccae1e3904\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            6 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66ca92963a149c3c12951f45\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            7 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66cfc75a0509101bec9cd8d2\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            8 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://view.genially.com/66d2792da0532d20500de8d9\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            else -> ""
        }
    }
}