package com.holoo.library.breadcrumb

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class BreadCrumbAdapter(private val listener: BreadCrumbView.AdapterClickListener) : RecyclerView.Adapter<BreadCrumbAdapter.ViewHolder>() {

    private var breadCrumbList: MutableList<BreadCrumb>? = null
    private var arrowDrawable: Int = R.drawable.ic_keyboard_arrow_right_black_24dp


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bread_crumb, parent, false), arrowDrawable, listener)
    }

    override fun getItemCount(): Int {
        return breadCrumbList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = breadCrumbList?.get(position)

        if (position == 0) {
            holder.image.visibility = View.GONE
        } else {
            holder.image.visibility = View.VISIBLE
        }

        holder.title.text = item?.getTitle()

    }

    fun setArrow(arrowDrawable: Int) {
        this.arrowDrawable = arrowDrawable
        notifyDataSetChanged()
    }

    fun setList(list: MutableList<BreadCrumb>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return breadCrumbList?.get(oldItemPosition)?.getTitle() == list[newItemPosition].getTitle()
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return breadCrumbList?.get(oldItemPosition) == list[newItemPosition]
            }

            override fun getOldListSize() = breadCrumbList?.size ?: 0

            override fun getNewListSize() = list.size
        })

        breadCrumbList = list

        diff.dispatchUpdatesTo(this)

    }

    fun addCrumb(breadCrumb: BreadCrumb) {
        breadCrumbList?.add(breadCrumb)
        breadCrumbList?.size?.minus(1)?.let { notifyItemInserted(it) }
    }

    fun getItem(position: Int): BreadCrumb? {
        return breadCrumbList?.get(position)
    }

    fun navigate(position: Int) {
        breadCrumbList?.let {
            val newList = it.subList(0, position.plus(1))
            setList(newList)
        }
    }

    fun getCurrentItem(): BreadCrumb? {
        return breadCrumbList?.let { it.get(it.size.minus(1)) }
    }

    fun goBack(): Boolean {

        breadCrumbList?.let {
            if (it.size > 1) {
                listener.onItemClick(null, it.size.minus(2))
                return true
            }
        }

        return false
    }

    class ViewHolder(itemView: View, val arrowDrawable: Int, val listener: BreadCrumbView.AdapterClickListener) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.crumb_title)
        var image: ImageView = itemView.findViewById(R.id.crumb_image)

        init {
            image.setImageResource(arrowDrawable)

            title.setOnClickListener {
                listener.onItemClick(it, adapterPosition)
            }

        }
    }
}