package com.example.zzz89.image_search_application.activity

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.zzz89.image_search_application.R
import com.example.zzz89.image_search_application.picture.PictureManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BasesActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()
    }

    private fun init() {
        val intent = this.intent

        setPicture(intent.getStringExtra("current"))
        setclickListener()
    }

    private fun setPicture(url: String) {
        Glide.with(this).load(url).into(detail_image)
    }

    private fun setclickListener() {
        detail_store.setOnClickListener(View.OnClickListener {
            processSaveImage()
        })
    }

    private fun processSaveImage(){
        if(checkStorePermission()){
            val pManager = PictureManager(this.applicationContext, detail_image.drawable)
            var imagename = intent.getStringExtra("title") + detail_image.hashCode().toString() + ".jpeg"
            val result = pManager.saveImage(imagename)

            if(result == null || !result.exists())
                Toast.makeText(this, "오류가 발생했습니다", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "이미지를 저장했습니다", Toast.LENGTH_LONG).show()

            var intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.setData(Uri.fromFile(result))
            sendBroadcast(intent)
        }
    }
}
