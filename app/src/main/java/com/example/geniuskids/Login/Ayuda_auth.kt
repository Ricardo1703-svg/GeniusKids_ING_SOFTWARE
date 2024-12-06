package com.example.geniuskids.Login

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.R

class Ayuda_auth : AppCompatActivity() {

    private lateinit var regre: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_auth)

        regre = findViewById(R.id.regresar)
        regre.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient() // Evita abrir navegadores externos
        webView.settings.javaScriptEnabled = true // Habilitar JavaScript si es necesario

        webView.loadUrl("https://view.genially.com/675313d01e1be6adbfe2fda9")
    }
    override fun onBackPressed() {
    }
}
