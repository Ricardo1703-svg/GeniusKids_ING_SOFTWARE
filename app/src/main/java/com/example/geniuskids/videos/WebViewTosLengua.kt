package com.example.geniuskids.videos

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Materias.MainActivityLenguaje
import com.example.geniuskids.R

class WebViewTosLengua : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos_lengua)

        val webView = findViewById<WebView>(R.id.webViewLenguaje)
        val videoHtml = intent.getStringExtra("VIDEO_HTML")

        webView.settings.javaScriptEnabled = true
        if (videoHtml != null) {
            webView.loadData(videoHtml, "text/html", "utf-8")
        }

        val regreifLenguaje = findViewById<ImageButton>(R.id.regreifLenguaje)
        regreifLenguaje.setOnClickListener {
            val intent = Intent(this, MainActivityLenguaje::class.java)
            startActivity(intent)
        }
    }
}