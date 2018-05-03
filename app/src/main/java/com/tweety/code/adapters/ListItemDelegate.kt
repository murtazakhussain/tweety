package com.tweety.code.adapters

import android.content.Context
import com.tweety.R
import com.tweety.code.models.Tweets
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

class ListItemDelegate(context: Context) : ItemViewDelegate<Tweets> {

    private var context: Context = context

    override fun getItemViewLayoutId(): Int {
        return R.layout.single_item_tweet
    }

    override fun convert(holder: ViewHolder?, item: Tweets?, position: Int) {
        holder?.setText(R.id.taxiName, item?.text)
        item?.apply {
        }
    }

    override fun isForViewType(item: Tweets?, position: Int): Boolean {
        return true
    }

}
