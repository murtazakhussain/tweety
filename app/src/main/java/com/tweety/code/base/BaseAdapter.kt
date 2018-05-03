package com.tweety.code.base

import android.content.Context
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter

class BaseAdapter<T> constructor(context: Context?, datas: MutableList<T>?) : MultiItemTypeAdapter<T>(context, datas) {

    fun updateData(data: List<T>){
        datas?.let {
            it.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun clearData(){
        datas.clear()
    }
}