package com.example.zzz89.image_search_application.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.zzz89.image_search_application.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()
    }

    private fun init() {
        val intent = this.intent

        setPicture(intent.getStringExtra("current"))
    }

    private fun setPicture(url: String) {
        Glide.with(this).load(url).into(detail_image)
    }
}
