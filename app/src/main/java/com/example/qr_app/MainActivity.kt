package com.example.qr_app

import android.Manifest
import android.R.attr.bitmap
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.WriterException


class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var btnGenerate: Button? = null
    var btnScan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        im = findViewById<ImageView>(R.id.imageView)
        btnGenerate = findViewById(R.id.button)
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        btnScan = findViewById(R.id.button2)
        btnScan?.setOnClickListener {
            checkCameraPermission()
        }
        btnGenerate?.setOnClickListener {
            val txt = editText.text.toString()
            generateQr(txt)
        }
    }
    private fun generateQr(text: String){
        val qrEncoder = QRGEncoder(text, null, QRGContents.Type.TEXT, 300)

        try {

            val bitmap = qrEncoder.encodeAsBitmap()

            im?.setImageBitmap(bitmap)
        } catch (e: WriterException) {

        }
    }
    private fun checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), 12)


        }else{
            startActivity(Intent(this, Scanner::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 12){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this, Scanner::class.java))

            }
        }
    }
}