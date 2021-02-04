package com.cgy.wanandroid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgy.wanandroid.R

/**
 * @author: cgy
 * @date: 2021/2/4 10:35 AM
 * @description:
 */
class WeChatFragment : Fragment() {

    companion object {
        fun getInstance() : WeChatFragment = WeChatFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}