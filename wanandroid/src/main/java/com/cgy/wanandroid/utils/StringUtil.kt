package com.cgy.wanandroid.utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author: cgy
 * @date: 2021/2/7 4:25 PM
 * @description:
 */
object StringUtil {

    fun getString(stream : InputStream) : String {
        val reader = BufferedReader(InputStreamReader(stream, "utf-8"))
        val sb = StringBuilder()
        var s : String? = reader.readLine()
        while (s != null) {
            sb.append(s).append("\n")
            s = reader.readLine()
        }
        return sb.toString()
    }
}