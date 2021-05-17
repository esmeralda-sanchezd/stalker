package com.example.stalker.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun ImageView.loadFromUrl(url: String){
    Glide.with(context)
        .load(url)
        .transform(CircleCrop())
        .into(this)
}