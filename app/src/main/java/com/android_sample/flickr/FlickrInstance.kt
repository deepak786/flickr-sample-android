package com.android_sample.flickr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FlickrInstance {

    // get the FlickrAPI handler
    fun getHandler(): FlickrAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FlickrAPI::class.java)
    }


}