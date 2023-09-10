package com.example.wistron_interview.attraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.databinding.FragmentAttractionBinding
import com.example.wistron_interview.ext.getVmFactory
import com.example.wistron_interview.network.LoadApiStatus

class AttractionFragment : BaseFragment() {

    private lateinit var binding: FragmentAttractionBinding
    private val viewModel by viewModels<AttractionViewModel> {
        getVmFactory(
            AttractionFragmentArgs.fromBundle(requireArguments()).attractionParams
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

        val recyclerView: RecyclerView = binding.attractionRecyclerView
        val attractionAdapter = AttractionAdapter(viewModel)
        recyclerView.adapter = attractionAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(viewModel.status.value == LoadApiStatus.LOADING) return

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (totalItemCount <= lastVisibleItem + 2) {
                    viewModel.loadMoreData()
                }
            }
        })

        viewModel.selectedPlace.observe(viewLifecycleOwner, Observer { place ->
            place?.let {
                val action = AttractionFragmentDirections.actionNavigationAttractionToNavigationDetail(it)
                findNavController().navigate(action)
                viewModel.selectPlace(null)
            }
        })

        viewModel.attractionItems.observe(viewLifecycleOwner, Observer {
            binding.toolbarTitle.text = viewModel.getLanguageSubTitleFromParams()
            attractionAdapter.submitList(it.data)
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