package com.example.wistron_interview.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.R
import com.example.wistron_interview.attraction.AttractionFragmentArgs
import com.example.wistron_interview.attraction.AttractionViewModel
import com.example.wistron_interview.databinding.FragmentHomeBinding
import com.example.wistron_interview.ext.getVmFactory


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> {
        getVmFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.updateCheckItemValue(basePosition)

        val recyclerView: RecyclerView = binding.districtRecyclerView
        val districtArray: Array<String> = resources.getStringArray(R.array.district_name)
        val districtList: List<String> = districtArray.toList()
        val adapter = DistrictAdapter(districtList, basePosition)
        recyclerView.adapter = adapter

        viewModel.checkItem.observe(viewLifecycleOwner, Observer {
            val adapter = DistrictAdapter(districtList, it)
            recyclerView.adapter = adapter
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.languagesChange.setOnClickListener {
            viewModel.checkItem.value?.let { checkItemValue ->
                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle(R.string.languages_choose).setSingleChoiceItems(
                        R.array.language_choose, checkItemValue
                    ) { _: DialogInterface, which: Int ->
                        viewModel.updateCheckItemValue(which)
                    }.setPositiveButton(R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showLoader(View.GONE)
    }

    companion object {
        const val basePosition = 0
    }
}