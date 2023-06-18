package com.mas.horizontalrecycleview.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mas.horizontalrecycleview.R
import com.mas.horizontalrecycleview.databinding.FragmentContentsBinding

class ContentsFragment : Fragment() {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var binding: FragmentContentsBinding
    private lateinit var viewModel: ContentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentsViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getContents(requireContext())
        viewModel.contents.observe(viewLifecycleOwner, Observer { contents ->
            contents?.let {
                binding.recyclerview.apply {
                    adapter = ContentsAdapter(contents.contents)
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            binding.textError.apply {
                text = it
                visibility = View.VISIBLE
            }
            binding.recyclerview.visibility = View.GONE
        })
    }

}