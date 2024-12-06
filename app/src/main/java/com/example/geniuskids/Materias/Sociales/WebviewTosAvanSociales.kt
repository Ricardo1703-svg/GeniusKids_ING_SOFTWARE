package com.example.geniuskids.Materias.Sociales

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R

class WebviewTosAvanSociales : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_tos_avan_sociales)

        webView = findViewById(R.id.webView)
        regre = findViewById(R.id.regresar)
        regre.setOnClickListener {
            val intent = Intent(this, Avanzado::class.java)
            startActivity(intent)
        }

        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""

        // Configurar el WebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(videoUrl)
    }
}