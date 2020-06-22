package com.potechto.easycuhk

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.os.Bundle
import android.widget.Toast
import android.view.ViewGroup
import android.content.Intent
import android.content.pm.PackageManager
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


class Scanner : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val ZXING_CAMERA_PERMISSION = 1
    private var mClss: Class<*>? = null

    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.scanner)
        val contentFrame = findViewById(R.id.content_frame) as ViewGroup
        mScannerView = ZXingScannerView(this)
        contentFrame.addView(mScannerView)
        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val warning = findViewById<TextView>(R.id.warning)
        var url_hearder: String? = null
        if (intent.getStringExtra("header") == "keepoll") {
            url_hearder = "https://poll.keep.edu.hk/"
            textView.setText("https://poll.keep.edu.hk/")
            editText.setHint("33193")
            warning.isVisible = false
        } else {
            url_hearder = "https://server5.ureply.mobi/student/mobile_index.php"
            textView.setText("Input Session no.")
            editText.setHint("LM2233")
            warning.isVisible = true
        }
        button.setOnClickListener() {
            startWebviwer(url_hearder + editText.text)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), ZXING_CAMERA_PERMISSION
            )
        }
    }

    public override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView?.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
        if (URLUtil.isValidUrl(rawResult.text)) {
            startWebviwer(rawResult.text)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ZXING_CAMERA_PERMISSION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClss != null) {
                        val intent = Intent(this, mClss)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Please grant camera permission to use the QR Scanner",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    public fun startWebviwer(url: String) {
        val intent = Intent(this, WebViewer::class.java)
        intent.putExtra("extra_url", url)
        startActivity(intent)
    }

}