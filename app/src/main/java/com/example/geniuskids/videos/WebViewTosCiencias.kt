package com.example.geniuskids.videos

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Intermedio.MainActivityInterCiencias
import com.example.geniuskids.R

class WebViewTosCiencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos_ciencias)

        val webView = findViewById<WebView>(R.id.webViewCiencias)
        val videoHtml = intent.getStringExtra("VIDEO_HTML")
        webView.settings.javaScriptEnabled = true
        if (videoHtml != null) {
            webView.loadData(videoHtml, "text/html", "utf-8")
        }

        val regreifCiencias = findViewById<ImageButton>(R.id.regreifCiencias)
        regreifCiencias.setOnClickListener {
            val intent = Intent(this, MainActivityInterCiencias::class.java)
            startActivity(intent)
        }
    }
}