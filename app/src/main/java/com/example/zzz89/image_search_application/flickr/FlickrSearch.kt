package com.example.zzz89.image_search_application.flickr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrSearch{
    val baseurl = "https://api.flickr.com/"
    val api_key = ""
    val method = "flickr.photos.search"
    val format = "json"
    val nojsoncallback = 1

    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder().apply {
            baseUrl(baseurl)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    fun getInterface(): FlickrInterface{
        return retrofit.create(FlickrInterface::class.java)
    }
}