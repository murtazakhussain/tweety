package com.tweety.code.base

import android.app.Activity
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.tweety.TweetyApp
import com.tweety.databinding.LayoutAppbarBinding
import com.tweety.networking.ServiceClass
import javax.inject.Inject


/**
 * Created by murtaza on 24/01/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var service: ServiceClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as TweetyApp).appComponent.inject(this)
        inflateView(savedInstanceState)
        setupToolbar()
        init(savedInstanceState)
        bindView()
        bindActionListeners()
    }

    private fun setupToolbar() {
        val binding = getToolbarLayoutBinding()
        if (binding is LayoutAppbarBinding) {
            setSupportActionBar(binding.toolbar)
        }
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

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected abstract fun inflateView(savedInstanceState: Bundle?)
    protected abstract fun getToolbarLayoutBinding(): ViewDataBinding
    protected abstract fun init(savedInstanceState: Bundle?)
    protected abstract fun bindActionListeners()
    protected abstract fun bindView()

}