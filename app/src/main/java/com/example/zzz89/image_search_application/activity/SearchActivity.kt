package com.example.zzz89.image_search_application.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.RelativeLayout
import com.example.zzz89.image_search_application.R
import com.example.zzz89.image_search_application.adapter.SearchAdapter
import com.example.zzz89.image_search_application.flickr.FlickrSearch
import com.example.zzz89.image_search_application.flickr.SearchResult
import com.example.zzz89.image_search_application.util.PicturePage
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private var searchAdapter: SearchAdapter = SearchAdapter()
    private var isTop: Boolean = false
    private var isLoad: Boolean = false

    private var keyword: String = ""
    private var pageutil: PicturePage = PicturePage()

    private var totalVisibleItemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initComponent()
    }

    fun initComponent(){
        search_recyclerview.adapter = searchAdapter
        search_recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!isLoad){
                    if(!recyclerView.canScrollVertically(1)){
                        isLoad = true
                        search(pageutil.page++)
                    }
                }
            }
        })

        search_button.setOnClickListener(View.OnClickListener {
            when(it.id) {
                R.id.search_button -> startSearch()
            }
            if(!isTop)
                edittextAnimation()
        })
    }

    fun edittextAnimation(){
        isTop = true
        search_Textlayout.animate().y(0f).setDuration(400).setInterpolator(AccelerateInterpolator())

        val param = search_Textlayout.layoutParams as RelativeLayout.LayoutParams
        param.removeRule(RelativeLayout.CENTER_IN_PARENT)
        param.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
        search_Textlayout.layoutParams = param
    }

    fun startSearch(){
        searchAdapter.clearItems()
        pageutil = PicturePage()
        search(pageutil.page)
    }

    fun search(page: Int){
        keyword = search_edittext.text.toString();
        Log.d("pagecount", page.toString())

        val flicker: FlickrSearch = FlickrSearch()
        val finter = flicker.getInterface()

        val finter_ready = finter.getSearchResut(
            flicker.method,
            flicker.api_key,
            keyword,
            page,
            pageutil.pagecount,
            flicker.format,
            flicker.nojsoncallback)

        finter_ready.enqueue(object: Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if(response.isSuccessful){
                    addItemtoAdapter(response.body()!!)
                }
            }
        })

        isLoad = false
    }

    fun addItemtoAdapter(body: SearchResult) {
        for(item in body.photos.photo!!)
            searchAdapter.addItem(item)

        val addSize = body.photos.photo!!.size
        val beforesize = searchAdapter.itemCount - addSize
        searchAdapter.notifyItemRangeChanged(beforesize, addSize)

    }
}
