package com.cgy.wanandroid.webclient

import android.webkit.WebViewClient

/**
 * @author: cgy
 * @date: 2021/2/7 4:16 PM
 * @description:
 */
object WebClientFactory {
    val JIAN_SHU = "https://www.jianshu.com"

    fun create(url : String) : WebViewClient {
        return when {
            url.startsWith(JIAN_SHU) -> JianShuWebClient()
            else -> BaseWebClient()
        }
    }
}