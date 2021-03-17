package com.cgy.wanandroid.ui.fragment

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseFragment
import com.cgy.wanandroid.utils.SettingUtil
import kotlinx.android.synthetic.main.fragment_about.*

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

    override fun attachLayoutRes(): Int = R.layout.fragment_about

    override fun initView(view: View) {
        about_content.run {
            text = Html.fromHtml(getString(R.string.about_content))
            movementMethod = LinkMovementMethod.getInstance()
        }

        val versionStr = getString(R.string.app_name) + "V" + activity?.packageManager?.getPackageInfo(activity?.packageName, 0)?.versionName
        about_version.text = versionStr

        setLogoBg()
    }

    private fun setLogoBg() {
        val drawable = iv_logo.background as GradientDrawable
        drawable.setColor(SettingUtil.getColor())
        iv_logo.setBackgroundDrawable(drawable)
    }

    override fun lazyLoad() {

    }
}