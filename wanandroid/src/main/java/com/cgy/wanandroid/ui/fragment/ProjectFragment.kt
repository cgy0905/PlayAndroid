package com.cgy.wanandroid.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgy.wanandroid.R

/**
 * @author: cgy
 * @date: 2021/2/4 10:44 AM
 * @description:
 */
class ProjectFragment : Fragment () {

    companion object {
        fun getInstance() : ProjectFragment = ProjectFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_square, container, false)
    }
}