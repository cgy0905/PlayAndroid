package com.cgy.wanandroid.mvp

import com.squareup.moshi.Json

/**
 * @author: cgy
 * @date: 2021/1/18 1:47 PM
 * @description:
 */

// 用户个人信息
data class UserInfoBody(
    @Json(name = "coinCount") val coinCount: Int, // 总积分
    @Json(name = "rank") val rank: Int, // 当前排名
    @Json(name = "userId") val userId: Int,
    @Json(name = "username") val username: String
)