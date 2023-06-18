package com.mas.horizontalrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mas.horizontalrecycleview.data.api.ApiClient
import com.mas.horizontalrecycleview.data.model.ContentsModel
import com.mas.horizontalrecycleview.ui.ContentsAdapter
import com.mas.horizontalrecycleview.databinding.ActivityMainBinding
import rx.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiClient.getContents(this, object : Observer<ContentsModel> {
            override fun onCompleted() {
                           }

            override fun onError(e: Throwable?) {
                Toast.makeText(this@MainActivity, e?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(contents: ContentsModel?) {
                if (contents == null) {
                    return
                }

                //Toast.makeText(this@MainActivity, contents.contents[0].title, Toast.LENGTH_SHORT).show()
                binding.recyclerview.apply {
                    adapter = ContentsAdapter(contents.contents)
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                }
            }

        })
    }
}