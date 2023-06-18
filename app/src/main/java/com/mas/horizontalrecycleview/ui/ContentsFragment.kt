package com.mas.horizontalrecycleview.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mas.horizontalrecycleview.R

class ContentsFragment : Fragment() {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contents, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}