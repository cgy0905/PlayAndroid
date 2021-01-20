package com.cgy.wanandroid.http

import android.content.Context
import android.content.SharedPreferences
import com.cgy.wanandroid.app.App
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.util.HashMap
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: cgy
 * @date: 2021/1/20 6:57 PM
 * @description:
 */
class PersistentCookieStore {

    private val LOG_TAG: String = "PersistentCookieStore"
    private val COOKIE_PREFS: String = "Cookies_Prefs"

    private val cookies: HashMap<String, ConcurrentHashMap<String, Cookie>> = HashMap()

    private val cookiePrefs: SharedPreferences

    init {
        cookiePrefs = App.context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE)
        var prefsMap = cookiePrefs.all
    }

    fun get(url : HttpUrl) : List<Cookie> {
        val list : ArrayList<Cookie> = ArrayList()
        if (cookies.containsKey(url.host())) {
            list.addAll(cookies[url.host()]?.values!!)
        }
        return list

    }

    fun removeAll() {
        val prefsWriter = cookiePrefs.edit()
        prefsWriter.clear()
        prefsWriter.apply()
        for (key in cookies.keys) {
            cookies.remove(key)
        }
    }
}