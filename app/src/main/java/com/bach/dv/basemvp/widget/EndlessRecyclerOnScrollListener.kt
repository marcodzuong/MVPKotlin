package com.bach.dv.basemvp.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    private var VISIBLE_THRESHOLD = 3
    private var PREVIOUS_TOTAL = 1
    private var CURRENT_PAGE = 0


    private var previousTotal =
        PREVIOUS_TOTAL // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private var visibleThreshold =
        VISIBLE_THRESHOLD // The minimum amount of items to have below your current scroll position before loading more.

    private var current_page = CURRENT_PAGE

    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var isLoadingAll = false

    fun EndlessRecyclerOnScrollListener(linearLayoutManager: LinearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager
    }

    fun initWithLoadSize20() {
        PREVIOUS_TOTAL = 19
        VISIBLE_THRESHOLD = 2
        CURRENT_PAGE = 0
    }

    fun reset() {
        visibleThreshold = VISIBLE_THRESHOLD
        current_page = CURRENT_PAGE
        previousTotal = PREVIOUS_TOTAL
        loading = true
        isLoadingAll = false
    }

    fun reset(previousTotal: Int) {
        visibleThreshold = VISIBLE_THRESHOLD
        current_page = CURRENT_PAGE
        this.previousTotal = previousTotal
        loading = true
        isLoadingAll = false
    }

    fun setCurrentPage(current_page: Int) {
        this.current_page = current_page
    }

    fun getCurrentPage(): Int {
        return current_page
    }

    fun setVisibleThreshold(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    fun setPreviousTotal(previousTotal: Int) {
        this.previousTotal = previousTotal
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var firstVisibleItem = 0
        var visibleItemCount = 0;
        visibleItemCount = recyclerView.childCount
        var totalItemCount = 0;
        totalItemCount = mLinearLayoutManager?.itemCount!!
        firstVisibleItem = mLinearLayoutManager?.findFirstVisibleItemPosition()!!
        //        int pos = 0;
        //        if (firstVisibleItem == 0) {
        //            pos = 0;
        //        } else {
        //            pos = firstVisibleItem + 1;
        //        }
        //        RecyclerView.OneChapterViewHolder vh = recyclerView.findViewHolderForAdapterPosition(pos);
        //        if (vh instanceof HomeFeedAdapter.CategoriesViewHolder) {
        //            ((HomeFeedAdapter.CategoriesViewHolder) vh).scrollTo();
        //        }
        if (!isLoadingAll) {
            if (totalItemCount == null) {
                return
            }
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                // End has been reached

                // Do something
                current_page++
                onLoadMore(current_page)
                loading = true
                isLoadingAll = true
            }
        }
    }

    fun setLoading(loading: Boolean) {
        this.isLoadingAll = loading
    }

    abstract fun onLoadMore(current_page: Int)
}