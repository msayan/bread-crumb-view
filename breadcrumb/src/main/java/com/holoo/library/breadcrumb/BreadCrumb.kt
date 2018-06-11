package com.holoo.library.breadcrumb

class BreadCrumb {
    private var title: String? = null
    private var tag: Any? = null

    fun getTitle() = title

    fun getTag() = tag

    class Builder() {

        private val crumb: BreadCrumb

        init {
            crumb = BreadCrumb()
        }

        fun setTitle(title: String): Builder {
            crumb.title = title
            return this
        }

        fun setTag(tag: Any): Builder {
            crumb.tag = tag
            return this
        }

        fun build(): BreadCrumb {
            return crumb
        }

    }
}