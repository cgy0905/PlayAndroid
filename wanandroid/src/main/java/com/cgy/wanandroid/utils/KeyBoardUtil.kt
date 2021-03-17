package com.cgy.wanandroid.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @author: chenguoyu
 * @date: 2021/1/18 21:23
 * @description:
 */
object KeyBoardUtil {

    /**
     * 是否落在 EditText区域
     */
    fun isHideKeyboard(view : View?, event : MotionEvent) : Boolean {
        if (view != null && view is EditText) {
            val location = intArrayOf(0, 0)
            view.getLocationInWindow(location)
            //获取现在拥有焦点的控件view的位置,即EditText
            val left = location[0]
            val top = location[1]
            val bottom = top + view.height
            val right = left + view.width
            //判断手指点击的区域是否落在EditText上面,如果不是,则返回true,否则返回false
            val isInEt = (event.x > left && event.x < right && event.y > top && event.y < bottom)
            return !isInEt
        }
        return false
    }

    /**
     * 关闭软键盘
     */
    fun hideKeyBoard(context: Context, view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 打开软键盘
     */
    fun openKeyBoard(editText : EditText, context : Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBoard(editText: EditText, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}