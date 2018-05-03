package com.tweety.code.presenters

import com.tweety.code.views.ListActivityView
import com.tweety.networking.ServiceClass

class ListActivityPresenter(private var view: ListActivityView, private var service: ServiceClass) {

    fun getTweets(handle: String) {
        if (isValid(handle)) {
            view.loadingVisibility(true)
            service.getTweetsOfHandle(handle).subscribe(
                    { list ->
                        if (list.isNotEmpty()) {
                            view.updateItems(list.toMutableList())
                            view.showHideLoaders(false)
                        } else {
                            view.showHideLoaders(true)
                        }
                    },
                    { error ->
                        view.showHideLoaders(true)
                    }
            )
        } else {
            view.showEmptyHandle()
        }
    }

    private fun isValid(handleString: String): Boolean {
        if (!handleString.isEmpty() || !handleString.isBlank()) {
            return true
        }

        return false
    }
}