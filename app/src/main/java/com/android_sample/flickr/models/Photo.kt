package com.android_sample.flickr.models

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val is_primary: Int,
    val has_comment: Int
) {
    fun getImageUrl(): String {
        return "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg"
    }
}