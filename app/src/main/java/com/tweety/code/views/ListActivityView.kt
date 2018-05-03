package com.tweety.code.views

import com.tweety.code.models.Tweets

interface ListActivityView {

    fun updateItems(listItems: MutableList<Tweets>)
    fun loadingVisibility(visible: Boolean)
    fun showInvalidText(value: String)
    fun showEmptyHandle()
    fun showNothingFound()
    fun showHideLoaders(toShow: Boolean)
}