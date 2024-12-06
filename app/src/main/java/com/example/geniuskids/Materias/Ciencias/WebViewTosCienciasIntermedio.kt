package com.example.geniuskids.Materias.Ciencias

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R

class WebViewTosCienciasIntermedio : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos_ciencias_intermedio)

        webView = findViewById(R.id.webView)
        regre = findViewById(R.id.regresar)
        regre.setOnClickListener {
            val intent = Intent(this, Intermedio::class.java)
            startActivity(intent)
        }

        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""

        // Configurar el WebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(videoUrl)
    }
}