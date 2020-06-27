package com.potechto.easycuhk

import android.net.http.SslError
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.widget.Toast
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.webkit.*
import android.widget.TextView


class WebViewer : AppCompatActivity() {
    private val webViewer: WebView by lazy {
        findViewById<WebView>(R.id.WebView)
    }
    private var backpressedTime: Long = System.currentTimeMillis()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        actionBar?.hide()
        supportActionBar?.hide()
        setContentView(R.layout.web_view)
        webViewer.webViewClient = object : WebViewClient() {
//            override fun onReceivedSslError(
//                view: WebView,
//                handler: SslErrorHandler,
//                error: SslError
//            ) {
//                if (error.primaryError != SslError.SSL_UNTRUSTED) {
//                    handler.proceed()
//                } else {
//                    Toast.makeText(applicationContext, "not trusted", Toast.LENGTH_SHORT).show()
//                    handler.cancel()
//                }
//            }
        }
        val MainButton: TextView = findViewById(R.id.main_button)
        MainButton.setOnClickListener {
            this.finish()
        }
        val settings = webViewer.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true

        settings.javaScriptCanOpenWindowsAutomatically = true

        webViewer.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val request = DownloadManager.Request(
                Uri.parse(url)
            )
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) //Notify client once download is completed!
            val fileName = URLUtil.guessFileName(url, contentDisposition, mimetype)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            Toast.makeText(
                applicationContext,
                "Downloading File", //To notify the Client that the file is being downloaded
                Toast.LENGTH_LONG
            ).show()
        })

        var url: String? = null
        url = intent.getStringExtra("extra_url")
        webViewer.loadUrl(url)

    }

    override fun onBackPressed() {
        if (backpressedTime + 1000 < System.currentTimeMillis()) {
            if (webViewer.canGoBack()) {
                // If web view have back history, then go to the web view back history
                webViewer.goBack()
//            toast("Going to back history")
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
//            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        }
        backpressedTime = System.currentTimeMillis()
    }
}