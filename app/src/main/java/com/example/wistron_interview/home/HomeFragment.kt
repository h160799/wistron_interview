package com.example.wistron_interview.home

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.BaseFragment
import com.example.wistron_interview.R
import com.example.wistron_interview.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.districtRecyclerView
        val districtArray: Array<String> = resources.getStringArray(R.array.district_name)
        val districtList: List<String> = districtArray.toList()
        val adapter = DistrictAdapter(districtList)
        recyclerView.adapter = adapter

        binding.languagesChange.setOnClickListener {
            var checkedItem = 0
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle(R.string.languages_choose)
                .setSingleChoiceItems(
                    R.array.language_choose,
                    checkedItem
                ) { _: DialogInterface, which: Int ->
                    checkedItem = which
                }
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        showLoader(View.GONE)
    }
}