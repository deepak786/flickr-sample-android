package com.android_sample.flickr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android_sample.flickr.databinding.ActivityFullScreenImageBinding


class FullScreenImage : AppCompatActivity() {

    companion object {
        val IMAGE_INTENT_EXTRA = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFullScreenImageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_full_screen_image)

        supportActionBar?.hide()

        binding.image = intent?.extras?.getString(IMAGE_INTENT_EXTRA, "")
    }
}