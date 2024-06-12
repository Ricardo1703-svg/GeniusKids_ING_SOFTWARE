package com.example.geniuskids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Base_de_Datos.DetalleContenidoActivity
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.videos.WebViewTos

class MainActivityMatematica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_matematica)

        val contenido1 = findViewById<Button>(R.id.btnIrConte_uno_mate)
        val contenido2 = findViewById<Button>(R.id.btnIrConte_dos_mate)
        val contenido3 = findViewById<Button>(R.id.btnIrConte_tres_mate)
        val contenido4 = findViewById<Button>(R.id.btnIrConte_cuatro_mate)

        contenido1.setOnClickListener {
            mostrarVideo(1)
        }

        contenido2.setOnClickListener {
            mostrarVideo(2)
        }

        contenido3.setOnClickListener {
            mostrarVideo(3)
        }

        contenido4.setOnClickListener {
            mostrarVideo(4)
        }

        //Barra de Nvegacion
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

    private fun mostrarContenido(id: String) {
        val intent = Intent(this, DetalleContenidoActivity::class.java)
        intent.putExtra("CONTENIDO_ID", id)
        startActivity(intent)
    }
    private fun mostrarVideo(video: Int) {
        val intent = Intent(this, WebViewTos::class.java)
        intent.putExtra("VIDEO_HTML", obtenerCodigoHTMLDelVideo(video))
        startActivity(intent)
    }

    private fun obtenerCodigoHTMLDelVideo(video: Int): String {
        return when (video) {
            1 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CT8wRHhVTRA?si=VTZTs4W2nbVM1BK2\" \n" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; \n" +
                    "gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            2 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7fJT0tFrjkg?si=jDMJsSth9-7PGvnG\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            3 -> "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XhL4XDWMhjw?si=rMZbktI8Uf7e0CF5\"" +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"

            4 ->"<iframe width=\"100%\" height=\"100%\"src=\"https://www.youtube.com/embed/amgrBjEH780?si=O5AV8GyNofrCgTTH\" " +
                    "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                    "encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"" +
                    "strict-origin-when-cross-origin\" allowfullscreen>" +
                    "</iframe>"
            else -> ""
        }
    }
}