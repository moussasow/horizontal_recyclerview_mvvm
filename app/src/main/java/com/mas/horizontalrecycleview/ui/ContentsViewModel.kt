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
    private val errorResponse = MutableLiveData<String?>()
    val error : LiveData<String?> get() = errorResponse

    fun getContents(context: Context) {
        ApiClient.getContents(context, object : Observer<ContentsModel> {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                errorResponse.value = e?.message
            }

            override fun onNext(contents: ContentsModel?) {
                response.value = contents
            }

        })
    }
}