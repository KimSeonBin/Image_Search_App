package com.example.zzz89.image_search_application.flickr

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrInterface{

    @GET("services/rest/")
    fun getSearchResut(@Query("method")     method: String,
                       @Query("api_key")    api_key: String,
                       @Query("text")       text: String,
                       @Query("page")       page: Int,
                       @Query("per_page")  per_page: Int,
                       @Query("format")     format: String,
                       @Query("nojsoncallback") no: Int): Call<SearchResult>
}