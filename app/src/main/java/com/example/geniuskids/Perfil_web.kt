package com.example.geniuskids

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Materias.Materias

class Perfil_web : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_web)

        webView = findViewById(R.id.webView)
        regre = findViewById(R.id.regresar)
        regre.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        // Obtener la URL del Intent
        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""

        // Configurar el WebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(videoUrl)
    }
    override fun onBackPressed() {
    }
}
