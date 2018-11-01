package com.example.zzz89.image_search_application.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.zzz89.image_search_application.R
import com.example.zzz89.image_search_application.activity.DetailActivity
import com.example.zzz89.image_search_application.flickr.FlickSearchItem
import kotlinx.android.synthetic.main.item_search.*

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val pictureArr: MutableList<FlickSearchItem> = ArrayList<FlickSearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var item = pictureArr[position]
        val url = "https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}" +
                "_${item.secret}_m.jpg"

        Glide.with(holder.itemView.context).load(url).into(holder.image)
        holder.image.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("current", url)
            holder.itemView.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return pictureArr.size
    }

    fun addItem(item: FlickSearchItem){
        pictureArr.add(item)
    }

    fun clearItems(){
        pictureArr.clear()
    }

    inner class SearchViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = view.findViewById(R.id.item_image) as ImageView
    }
}