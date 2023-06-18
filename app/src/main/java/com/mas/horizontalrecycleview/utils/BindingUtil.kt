package com.mas.horizontalrecycleview.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mas.horizontalrecycleview.data.model.Image
import com.squareup.picasso.Picasso

@BindingAdapter("content_image")
fun loadImage(view: ImageView, image: Image) {
    Picasso.get().load(image.url).into(view)
}