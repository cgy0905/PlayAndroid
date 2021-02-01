package com.cgy.wanandroid.ext

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Checkable
import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.app.App
import com.cgy.wanandroid.widget.CustomToast
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: cgy
 * @date: 2021/1/18 3:19 PM
 * @description:
 */
fun Any.loge(content: String?) {
    loge(this.javaClass.simpleName ?: App.TAG, content ?: "")
}

fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: "")
}

fun Fragment.showToast(content: String) {
    CustomToast(this?.activity?.applicationContext, content).show()
}

fun Context.showToast(content: String) {
    CustomToast(this, content).show()
}

fun Activity.showSnackMsg(msg: String) {
    val snackbar = Snackbar.make(this.window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(R.id.snackbar_text)
        .setTextColor(ContextCompat.getColor(this, R.color.white))
    snackbar.show()
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

inline fun <T : View> T.setSingleClickListener(time: Long = 1000, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

/**
 * getAgentWeb
 */
fun String.getAgentWeb(
    activity: Activity,
    webContent: ViewGroup,
    layoutParams: ViewGroup.LayoutParams,
    webView: WebView,
    webViewClient: WebViewClient,
    webChromeClient: WebChromeClient?,
    indicatorColor: Int
): AgentWeb = AgentWeb.with(activity) //传入activity or fragment
    .setAgentWebParent(webContent, 1, layoutParams) //传入AgentWeb 的父控件
    .useDefaultIndicator(indicatorColor, 2)//传入默认进度条
    .setWebView(webView)
    .setWebViewClient(webViewClient)
    .setWebChromeClient(webChromeClient)
    .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
    .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时,弹窗咨询用户是否前往其他应用
    .interceptUnkownUrl()
    .createAgentWeb()
    .ready()
    .go(this)

/**
 * 格式化当前日期
 */
fun formatCurrentDate() : String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

/**
 * String 转 Calendar
 */
fun String.stringToCalendar() : Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar
}