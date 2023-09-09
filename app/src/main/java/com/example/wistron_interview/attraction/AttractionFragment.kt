package com.example.wistron_interview.attraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.databinding.FragmentAttractionBinding

class AttractionFragment : BaseFragment() {

    private lateinit var viewModel: AttractionViewModel
    private lateinit var binding: FragmentAttractionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttractionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.attractionRecyclerView

        val adapter = AttractionAdapter(listOf(""))
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
