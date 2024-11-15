package com.example.geniuskids.videos

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Intermedio.MainActivityInterSociales
import com.example.geniuskids.R

class WebViewTosSocIntermedio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos_soc_intermedio)

        val webView = findViewById<WebView>(R.id.webViewSocialesInter)
        val videoHtml = intent.getStringExtra("VIDEO_HTML")
        webView.settings.javaScriptEnabled = true
        if (videoHtml != null) {
            webView.loadData(videoHtml, "text/html", "utf-8")
        }

        val regreif = findViewById<ImageButton>(R.id.regreifSocialesInter)
        regreif.setOnClickListener {
            val intent = Intent(this, MainActivityInterSociales::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
    }
}