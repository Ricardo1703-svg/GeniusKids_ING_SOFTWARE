package com.example.geniuskids

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewTos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_tos)

        val webView : WebView = findViewById(R.id.video)

        val video = "<iframe width=\"425\" height=\"315\" src=\"https://www.youtube.com/embed/CT8wRHhVTRA?si=VTZTs4W2nbVM1BK2\" \n" +
                "        title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; \n" +
                "        gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"

        webView.loadData(video,"text/html","utf-8")
        webView.settings.javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()
    }
}