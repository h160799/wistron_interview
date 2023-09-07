package com.example.wistron_interview

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.districtRecyclerView


//        val viewModel: MainViewModel by lazy {
//            ViewModelProvider(this, MainViewModel.Factory(repository))[MainViewModel::class.java]
//        }

        val adapter: DistrictAdapter = DistrictAdapter(listOf(""))
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}