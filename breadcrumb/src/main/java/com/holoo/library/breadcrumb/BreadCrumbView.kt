package com.holoo.library.breadcrumb

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout


class BreadCrumbView : FrameLayout {

    private var recyclerView: RecyclerView? = null
    private var breadCrumbClickListener: Listener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreadCrumbView, defStyleAttr, 0)
            val arrowDrawable = typedArray.getResourceId(R.styleable.BreadCrumbView_arrow_drawable, -1)
            typedArray.recycle()
            if (arrowDrawable != -1 && recyclerView?.adapter is BreadCrumbAdapter) {
                (recyclerView?.adapter as BreadCrumbAdapter).setArrow(arrowDrawable)
            }
        }
    }

    private fun init() {

        if (recyclerView == null) {

            val recyclerLayoutParams = ViewGroup.LayoutParams(-1, -1)
            recyclerLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT

            recyclerView = RecyclerView(context)
            recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            recyclerView?.adapter = BreadCrumbAdapter(object : AdapterClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    breadCrumbClickListener?.let {
                        if (recyclerView?.adapter is BreadCrumbAdapter) {
                            val item = (recyclerView?.adapter as BreadCrumbAdapter).getItem(position)
                            item?.let { crumb -> it.onBreadCrumbSelected(crumb) }
                            itemClicked(position)
                        }
                    }
                }

            })

            addView(recyclerView, recyclerLayoutParams)
        }

    }

    fun itemClicked(position: Int) {

        if (recyclerView?.adapter is BreadCrumbAdapter) {
            (recyclerView?.adapter as BreadCrumbAdapter).navigate(position)
        }

    }

    fun getCurrentCrumb(): BreadCrumb? {
        return if (recyclerView?.adapter is BreadCrumbAdapter) {
            (recyclerView?.adapter as BreadCrumbAdapter).getCurrentItem()
        } else null
    }

    fun setBreadCrumbList(list: MutableList<BreadCrumb>) {
        if (recyclerView?.adapter is BreadCrumbAdapter) {
            (recyclerView?.adapter as BreadCrumbAdapter).setList(list)
        }

    }

    fun setListener(listener: Listener) {
        if (recyclerView?.adapter is BreadCrumbAdapter) {
            this.breadCrumbClickListener = listener
        }
    }

    fun addBreadCrumb(breadCrumb: BreadCrumb) {
        recyclerView?.let {
            if (it.adapter is BreadCrumbAdapter) {
                (it.adapter as BreadCrumbAdapter).addCrumb(breadCrumb)
            }

            postDelayed(object : Runnable {
                override fun run() {
                    it.smoothScrollToPosition(it.adapter.itemCount.minus(1))
                }

            }, 500)
        }
    }

    fun goBack(): Boolean {
        if (recyclerView?.adapter is BreadCrumbAdapter) {
           return (recyclerView?.adapter as BreadCrumbAdapter).goBack()
        }
        return false
    }

    interface Listener {
        fun onBreadCrumbSelected(crumb: BreadCrumb)
    }

    interface AdapterClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}