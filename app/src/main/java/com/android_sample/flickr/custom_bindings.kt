package com.android_sample.flickr

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object CustomBindings {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun image(imageView: ImageView?, imageUrl: String?) {
        if (imageView != null && imageView.context != null) if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        } else {
            imageView.setImageBitmap(null)
        }
    }
}