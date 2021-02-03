package com.cgy.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.cgy.wanandroid.MainActivity
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private var alphaAnimation : AlphaAnimation? = null

    override fun attachLayoutRes(): Int  = R.layout.activity_splash

    override fun useEventBus(): Boolean  = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F,1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(animation: Animation?) {
                    TODO("Not yet implemented")
                }

            })
        }
        layout_splash.startAnimation(alphaAnimation)
    }

    override fun initColor() {
        super.initColor()
        layout_splash.setBackgroundColor(mThemeColor)
    }

    override fun start() {
    }

    fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}