package com.tweety.code.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.tweety.R
import com.tweety.code.adapters.ListItemDelegate
import com.tweety.code.base.BaseActivity
import com.tweety.code.base.BaseAdapter
import com.tweety.code.models.Tweets
import com.tweety.code.presenters.ListActivityPresenter
import com.tweety.code.views.ListActivityView
import com.tweety.databinding.LayoutAppbarBinding
import com.tweety.databinding.LayoutListOfTweetsBinding
import kotlinx.android.synthetic.main.layout_list_of_tweets.*

class ListActivity : BaseActivity(), ListActivityView {

    private lateinit var viewBindings: LayoutListOfTweetsBinding
    private lateinit var presenter: ListActivityPresenter
    private lateinit var baseAdapter: BaseAdapter<Tweets>
    val listItems: MutableList<Tweets> = mutableListOf()

    override fun init(savedInstanceState: Bundle?) {
        presenter = ListActivityPresenter(this, service)
        presenter.getTweets("twitterapi")
    }

    private fun initList() {
        itemsList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        baseAdapter = BaseAdapter(this, listItems)
        baseAdapter.addItemViewDelegate(ListItemDelegate(this))
        viewBindings.itemsList.adapter = baseAdapter
    }

    override fun bindView() {
        initList()
    }

    override fun bindActionListeners() {
        tweety.setOnClickListener { view -> getTweets() }
    }

    override fun updateItems(listItems: MutableList<Tweets>) {
        emptyVisibility(false)
        this.listItems.clear()
        baseAdapter.clearData()
        this.listItems.addAll(listItems)
        baseAdapter.updateData(this.listItems)
    }

    override fun inflateView(savedInstanceState: Bundle?) {
        viewBindings = DataBindingUtil.setContentView(this, R.layout.layout_list_of_tweets)
    }

    override fun getToolbarLayoutBinding(): LayoutAppbarBinding {
        return viewBindings.appbarLayout
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return false
    }

    override fun loadingVisibility(visible: Boolean) {
        viewBindings.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun emptyVisibility(visible: Boolean) {
        nothingFound.visibility = if (visible) View.VISIBLE else View.GONE
    }


    private fun getTweets() {
        hideKeyboard(this)
        presenter.getTweets(tweetyHandle.text.toString())
    }

    override fun showInvalidText(value: String) {
        //Nothing for now
    }

    override fun showNothingFound() {
        loadingVisibility(false)
        emptyVisibility(true)
        itemsList.visibility = View.GONE
    }

    override fun showEmptyHandle() {
        Toast.makeText(this, "Smarty! there is no empty handle on Twitter", Toast.LENGTH_SHORT).show()
    }

    override fun showHideLoaders(toShow: Boolean) {
        if (toShow) {
            loadingVisibility(false)
            emptyVisibility(true)
            itemsList.visibility = View.INVISIBLE
            return
        }

        loadingVisibility(false)
        emptyVisibility(false)
        itemsList.visibility = View.VISIBLE
    }
}
