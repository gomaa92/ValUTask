package com.valu.exercise_01.base.persentation.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.valu.exercise_01.R

object Utils {

    fun loadImage(context: Context, imageView: ImageView?, imageUlr: String?) {
        if (imageView != null)
            Glide.with(context).load(imageUlr).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
    }
}