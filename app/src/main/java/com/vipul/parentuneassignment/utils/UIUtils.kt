package com.vipul.parentuneassignment.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.vipul.parentuneassignment.R

object UIUtils {

    fun setNetworkImage(context: Context, imageUrl: String, view: ImageView) {
        val requestOptions = RequestOptions().dontAnimate().error(R.drawable.ic_lock)
            .placeholder(R.drawable.ic_lock).diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context).load(imageUrl).apply(requestOptions).into(view)
    }
}