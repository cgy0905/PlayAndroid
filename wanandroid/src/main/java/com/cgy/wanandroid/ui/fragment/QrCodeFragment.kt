package com.cgy.wanandroid.ui.fragment

import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseFragment

/**
 * @author: cgy
 * @date: 2021/3/3 11:47 AM
 * @description:
 */
class QrCodeFragment : BaseFragment() {

    companion object {
        fun getInstance(): QrCodeFragment = QrCodeFragment()
    }

    override fun attachLayoutRes(): Int  = R.layout.fragment_qr_code

    override fun initView(view: View) {

    }

    override fun lazyLoad() {

    }
}