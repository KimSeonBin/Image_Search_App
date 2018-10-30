package com.example.zzz89.image_search_application.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.zzz89.image_search_application.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    override fun onResume() {
        super.onResume()
        sleepSometimes()
    }

    fun sleepSometimes(){
        Handler().postDelayed({goToSearchActivity()}, 1300)
    }

    fun goToSearchActivity(){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }
}
