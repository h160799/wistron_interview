package com.example.wistron_interview.home

import android.content.Context
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
import com.example.wistron_interview.TaipeiTravelApplication
import com.example.wistron_interview.data.DistrictNameZH
import com.example.wistron_interview.databinding.FragmentHomeBinding
import com.example.wistron_interview.ext.getVmFactory


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val itemHeights: MutableList<Int> = MutableList(DistrictNameZH.values().size) { 0 }
    private val viewModel by viewModels<HomeViewModel> {
        getVmFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basePosition = TaipeiTravelApplication.instance
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            ?.getInt(KEY_BASE_POSITION, 0) ?: 0

        viewModel.updateCheckItemValue(basePosition)

        val recyclerView: RecyclerView = binding.districtRecyclerView
        val adapter = DistrictAdapter(basePosition, itemHeights)
        recyclerView.adapter = adapter

        viewModel.checkItem.observe(viewLifecycleOwner, Observer {
            val updateAdapter = DistrictAdapter(it, itemHeights)
            recyclerView.adapter = updateAdapter
        })

        viewModel.languageTitle.observe(viewLifecycleOwner, Observer { title ->
            binding.toolbarTitle.text = title
        })

        binding.languagesChange.setOnClickListener {
            viewModel.checkItem.value?.let { checkItemValue ->
                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle(R.string.languages_choose).setSingleChoiceItems(
                        R.array.language_choose, checkItemValue
                    ) { _: DialogInterface, which: Int ->
                        basePosition = which
                        viewModel.updateCheckItemValue(which)
                    }.setCancelable(false)
                    .setPositiveButton(R.string.ok) { dialog, _ ->
                        val sharedPref = activity?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                        with (sharedPref?.edit()) {
                            this?.putInt(KEY_BASE_POSITION, basePosition)
                            this?.commit()
                        }
                        dialog.dismiss()
                    }.show()
            }
        }
    }

    companion object {
        var basePosition = 0
        private const val PREF_NAME = "Select_Language"
        private const val KEY_BASE_POSITION = "basePosition"
    }
}