package com.example.permisionacces

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "Permision"

    private val REQUEST_RECORD_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPermision()
    }

    private fun setPermision() {
        val permission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission denied")
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.RECORD_AUDIO
            )
        ) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Woy Gua Butuh record anjay")
            builder.setTitle("Butuh anjay")
            builder.setPositiveButton("OK")
            { dialogInterface, wich ->
                Log.d(TAG, "Tekan")
            }
            val dialog = builder.create()
            dialog.show()
        } else {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.RECORD_AUDIO),
            REQUEST_RECORD_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_RECORD_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Udah di tolak")
                } else {
                    Log.d(TAG, "Diterima")
                }
            }
        }

    }
}

