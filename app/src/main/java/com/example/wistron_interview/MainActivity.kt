package com.example.wistron_interview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wistron_interview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

    }

    override fun onResume() {
        super.onResume()
        setLoader(View.GONE)
    }

    fun setLoader(isVisible: Int) {
        binding.progressBar.visibility = isVisible
    }
}