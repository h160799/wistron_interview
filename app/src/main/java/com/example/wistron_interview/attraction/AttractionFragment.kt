package com.example.wistron_interview.attraction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.databinding.FragmentAttractionBinding
import com.example.wistron_interview.ext.getVmFactory
import com.example.wistron_interview.network.LoadApiStatus
import com.example.wistron_interview.util.Logger

class AttractionFragment : BaseFragment() {

    private lateinit var binding: FragmentAttractionBinding
    private val viewModel by viewModels<AttractionViewModel> {
        getVmFactory(
            AttractionFragmentArgs.fromBundle(requireArguments()).lang,
            AttractionFragmentArgs.fromBundle(requireArguments()).page,
            AttractionFragmentArgs.fromBundle(requireArguments()).nLat,
            AttractionFragmentArgs.fromBundle(requireArguments()).eLong
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAttractionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.attractionItems.observe(viewLifecycleOwner, Observer {
            Logger.e(it.total.toString())
            val recyclerView: RecyclerView = binding.attractionRecyclerView
            val adapter = AttractionAdapter(it.data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.status.observe(viewLifecycleOwner, Observer{
            if (it == LoadApiStatus.LOADING){
                binding.loadingLottie.visibility = View.VISIBLE
            }else{
                binding.loadingLottie.visibility = View.GONE
            }
        })





        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
