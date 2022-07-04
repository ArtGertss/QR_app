package com.example.qr_app

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.util.zip.ZipEntry

class Scanner : AppCompatActivity() , ZBarScannerView.ResultHandler {
    private lateinit var zbView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        zbView = ZBarScannerView(this)
        setContentView(zbView)


    }

    override fun onRestart() {
        super.onRestart()
        zbView.setResultHandler(this)
        zbView.startCamera()
    }
    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        Log.d(TAG, "Result${result?.contents}")
        finish()
    }
}