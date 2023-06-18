package com.mas.horizontalrecycleview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mas.horizontalrecycleview.R
import com.mas.horizontalrecycleview.data.model.Content
import com.mas.horizontalrecycleview.databinding.ViewItemContentBinding

class ContentsAdapter(private val items: List<Content>) : RecyclerView.Adapter<ContentsAdapter.ViewHolder>(){


    inner class ViewHolder(val recyclerviewBinding: ViewItemContentBinding)
        : RecyclerView.ViewHolder(recyclerviewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_item_content,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerviewBinding.content = items[position]
    }

    override fun getItemCount() = items.size
}