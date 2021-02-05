package com.cgy.wanandroid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgy.wanandroid.R
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

/**
 * @author: cgy
 * @date: 2021/2/4 10:35 AM
 * @description:
 */
class HomeFragment : Fragment() {

    companion object {
        fun getInstance() : HomeFragment = HomeFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun lazyLoad() {

    }


//    override fun scrollToTop() {
//        recyclerView.run {
//            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
//                scrollToPosition(0)
//            } else {
//                smoothScrollToPosition(0)
//            }
//        }
//    }
}