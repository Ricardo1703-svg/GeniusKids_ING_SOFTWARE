package com.example.geniuskids.Materias

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R

class Ayuda_Materia : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_materia)

        regre = findViewById(R.id.regresar)
        regre.setOnClickListener {
            val intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient() // Evita abrir navegadores externos
        webView.settings.javaScriptEnabled = true // Habilitar JavaScript si es necesario

        webView.loadUrl("https://view.genially.com/67531e59206fc9895595efcb")

    }
    override fun onBackPressed() {
    }
}