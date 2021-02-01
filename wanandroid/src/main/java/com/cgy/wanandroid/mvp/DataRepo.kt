package com.cgy.wanandroid.mvp

import com.cgy.wanandroid.mvp.model.BaseBean
import com.squareup.moshi.Json

/**
 * @author: cgy
 * @date: 2021/1/18 1:47 PM
 * @description:
 */
data class HttpResult<T>(
    @Json(name = "data") val data : T
) : BaseBean()
// 用户个人信息
data class UserInfoBody(
    @Json(name = "coinCount") val coinCount: Int, // 总积分
    @Json(name = "rank") val rank: Int, // 当前排名
    @Json(name = "userId") val userId: Int,
    @Json(name = "username") val username: String
)

data class Banner(
    @Json(name = "desc") val desc : String,
    @Json(name = "id") val id: Int,
    @Json(name = "imagePath") val imagePath: String,
    @Json(name = "isVisible") val isVisible: Int,
    @Json(name = "order") val order: Int,
    @Json(name = "title") val title: String,
    @Json(name = "type") val type: Int,
    @Json(name = "url") val url: String
)