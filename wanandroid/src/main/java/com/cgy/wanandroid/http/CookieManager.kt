package com.cgy.wanandroid.http

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @author: cgy
 * @date: 2021/1/20 5:34 PM
 * @description:
 */
class CookieManager : CookieJar {

    private val COOKIE_STORE = PersistentCookieStore()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookies ?: return
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> = COOKIE_STORE.get(url)

    fun clearAllCookies() {
        COOKIE_STORE.removeAll()
    }

}