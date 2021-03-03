package com.cgy.wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.cgy.wanandroid.base.BaseFragment

/**
 * @author: cgy
 * @date: 2021/3/3 11:49 AM
 * @description:
 */
class AboutFragment : BaseFragment() {

    companion object {
        fun getInstance(bundle: Bundle): AboutFragment {
            val fragment = AboutFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun attachLayoutRes(): Int {
        TODO("Not yet implemented")
    }

    override fun initView(view: View) {
        TODO("Not yet implemented")
    }

    override fun lazyLoad() {
        TODO("Not yet implemented")
    }
}