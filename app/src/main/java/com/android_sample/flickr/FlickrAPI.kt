package com.android_sample.flickr

import com.android_sample.flickr.models.Gallery
import retrofit2.Call
import retrofit2.http.GET

interface FlickrAPI {

    @GET("services/rest/?method=flickr.galleries.getPhotos&api_key=8ea267b0da9fbf62a82e17e8b2e19bd2&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1")
    fun getGallery(): Call<Gallery>
}