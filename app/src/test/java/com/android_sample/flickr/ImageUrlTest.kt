package com.android_sample.flickr

import com.android_sample.flickr.models.Photo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImageUrlTest {
    // given model data
    private val photo = Photo(
        "8432423659",
        "37107167@N07",
        "dd1b834ec5",
        "8187",
        9,
        "Color",
        1,
        0,
        0,
        1,
        0
    )
    private val expected = "https://farm9.staticflickr.com/8187/8432423659_dd1b834ec5.jpg"
    private var result = ""

    @Before
    fun whenCondition() {
        result = photo.getImageUrl()
    }

    @Test
    fun thenCondition() {
        Assert.assertEquals(expected, result)
    }
}