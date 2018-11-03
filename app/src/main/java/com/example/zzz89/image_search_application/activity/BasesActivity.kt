package com.example.zzz89.image_search_application.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BasesActivity: AppCompatActivity(){
    protected val writeP = Manifest.permission.WRITE_EXTERNAL_STORAGE

    fun checkInternet(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        if(null == activeNetwork) {
            Toast.makeText(this, "인터넷 연결을 확인해 주세요", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun checkStorePermission(): Boolean {
        val writePcheck = ContextCompat.checkSelfPermission(this, writeP)
        val permissonList = ArrayList<String>()

        if(writePcheck != PackageManager.PERMISSION_GRANTED)
            permissonList.add(writeP)

        if(!permissonList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissonList.toTypedArray(), 2)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            code_storage -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "저장소 권한을 허락해 주세요.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        val code_storage: Int = 2
    }
}