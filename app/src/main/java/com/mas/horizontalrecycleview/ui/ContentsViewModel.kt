package com.mas.horizontalrecycleview.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mas.horizontalrecycleview.data.api.ApiClient
import com.mas.horizontalrecycleview.data.model.ContentsModel
import rx.Observer

class ContentsViewModel() : ViewModel() {
    private val response = MutableLiveData<ContentsModel?>()
    val contents : LiveData<ContentsModel?> get() = response

    fun getContents(context: Context) {
        ApiClient.getContents(context, object : Observer<ContentsModel> {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                Toast.makeText(context, e?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(contents: ContentsModel?) {
                response.value = contents
            }

        })
    }
}