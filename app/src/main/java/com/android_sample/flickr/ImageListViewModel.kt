package com.android_sample.flickr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android_sample.flickr.models.Gallery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : ViewModel() {

    // live data that will hold the response of Flickr API
    val gallery: MutableLiveData<Gallery> = MutableLiveData()

    // loading variable
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var call: Call<Gallery>

    // load the images from Flickr
    fun loadImages() {
        if (gallery?.value == null) {
            // load the data from API

            loading.postValue(true)

            call = FlickrInstance().getHandler().getGallery()
            call.enqueue(object : Callback<Gallery> {
                override fun onResponse(call: Call<Gallery>, response: Response<Gallery>) {
                    gallery.postValue(response.body())
                    loading.postValue(false)
                }

                override fun onFailure(call: Call<Gallery>, t: Throwable) {
                    t.printStackTrace()
                    loading.postValue(false)
                }

            })
        }
    }

    override fun onCleared() {
        super.onCleared()

        // cancel the call
        call?.cancel()
    }

}