package com.example.wistron_interview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launch)

        val SPLASH_TIME_OUT = 2000
        val homeIntent = Intent(this@LaunchActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}