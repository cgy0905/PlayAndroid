package com.cgy.wanandroid.ui.activity

import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseActivity

class SplashActivity : BaseActivity() {

    private var alphaAnimation : AlphaAnimation? = null

    override fun attachLayoutRes(): Int  = R.layout.activity_splash

    override fun useEventBus(): Boolean  = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun start() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}