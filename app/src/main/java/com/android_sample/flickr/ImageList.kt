package com.android_sample.flickr

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android_sample.flickr.databinding.ActivityImageListBinding
import com.android_sample.flickr.models.Photo

class ImageList : AppCompatActivity(), RecyclerItemClickListener {
    private lateinit var adapter: ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityImageListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_image_list)

        // set up the toolbar

        // load or create the view model
        // from the (activity/fragment)-ktx artifact
        val viewModel: ImageListViewModel by viewModels()

        // load the images from Flickr
        viewModel.loadImages()

        // observe the loading variable
        viewModel.loading.observe(this, {
            if (it) {
                binding.loader.show()
            } else {
                binding.loader.hide()
            }
        })

        // observe for the response of data
        viewModel.gallery.observe(this, {
            // update the adapter
            adapter?.submitList(it?.photos?.photo)
        })

        // set the adapter
        binding.imageList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        adapter = ImageListAdapter(this)
        binding.imageList.adapter = adapter
    }

    override fun onRecyclerItemClick(position: Int, obj: Any?) {
        if (obj is Photo) {
            val intent = Intent(this, FullScreenImage::class.java)
            intent.putExtra(FullScreenImage.IMAGE_INTENT_EXTRA, obj.getImageUrl())
            startActivity(intent)
        }
    }
}