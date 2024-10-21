package com.example.geniuskids.videos

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Intermedio.MainActivityInterMatematicas
import com.example.geniuskids.R

class WebViewTosMateIntermedio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view_tos_mate_intermedio)

        val webView = findViewById<WebView>(R.id.webView)
        val videoHtml = intent.getStringExtra("VIDEO_HTML")
        webView.settings.javaScriptEnabled = true
        if (videoHtml != null) {
            webView.loadData(videoHtml, "text/html", "utf-8")
        }

        val regreif = findViewById<ImageButton>(R.id.regreif)
        regreif.setOnClickListener {
            val intent = Intent(this, MainActivityInterMatematicas::class.java)
            startActivity(intent)
        }
    }
}