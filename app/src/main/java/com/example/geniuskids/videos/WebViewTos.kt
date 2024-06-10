package com.example.geniuskids.videos

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.MainActivityMatematica
import com.example.geniuskids.R

class WebViewTos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos)

        val webView = findViewById<WebView>(R.id.webView)
        val videoHtml = intent.getStringExtra("VIDEO_HTML")
        webView.settings.javaScriptEnabled = true
        if (videoHtml != null) {
            webView.loadData(videoHtml, "text/html", "utf-8")
        }

        val regreif = findViewById<ImageButton>(R.id.regreif)
        regreif.setOnClickListener {
            val intent = Intent(this, MainActivityMatematica::class.java)
            startActivity(intent)
        }
    }
}
