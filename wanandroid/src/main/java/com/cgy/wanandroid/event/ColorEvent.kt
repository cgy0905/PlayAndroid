package com.cgy.wanandroid.event

import com.cgy.wanandroid.utils.SettingUtil

/**
 * @author: cgy
 * @date: 2021/2/7 3:20 PM
 * @description:
 */
class ColorEvent(var isRefresh : Boolean, var color : Int = SettingUtil.getColor())