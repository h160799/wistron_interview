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

        val recyclerView: RecyclerView = binding.districtRecyclerView
        viewModel.basePosition.observe(viewLifecycleOwner, Observer { position ->
            val adapter = DistrictAdapter(position, itemHeights)
            recyclerView.adapter = adapter
            viewModel.updateCheckItemValue(position)
        })

        viewModel.checkItem.observe(viewLifecycleOwner, Observer {
            val updateAdapter = DistrictAdapter(it, itemHeights)
            recyclerView.adapter = updateAdapter
        })

        viewModel.languageTitle.observe(viewLifecycleOwner, Observer { title ->
            binding.toolbarTitle.text = title
        })

        viewModel.showLanguageDialog.observe(viewLifecycleOwner, Observer { show ->
            if (show) {
                val checkItemValue = viewModel.checkItem.value ?: 0
                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle(R.string.languages_choose).setSingleChoiceItems(
                        R.array.language_choose, checkItemValue
                    ) { _: DialogInterface, which: Int ->
                        viewModel.saveLanguagePreference(which)
                    }.setCancelable(false).setPositiveButton(R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
                viewModel.showLanguageDialog.value = false
            }
        })

        binding.languagesChange.setOnClickListener {
            viewModel.changeLanguage()
        }
    }
}