package com.example.zzz89.image_search_application.flickr

data class SearchResult(
    var photos: Photos
)

class Photos{
    var page: Int? = null
    var pages: Int? = null
    var perpage: Int? = null
    var total: Int? = null
    var photo: ArrayList<FlickSearchItem>? = null
}

data class FlickSearchItem(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int
)