package com.cgy.wanandroid.http.function

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * @author: cgy
 * @date: 2021/1/22 4:36 PM
 * @description:
 */
class RetryWithDelay : Function<Observable<out Throwable>, Observable<*>>{

    private var maxRetryCount = 3 //可重试次数
    private var retryDelayMillis : Long = 3000  //重试等待时间

    constructor() {}

    constructor(retryDelayMillis : Long) {
        this.retryDelayMillis = retryDelayMillis
    }

    constructor(maxRetryCount : Int, retryDelayMillis: Long) {

    }



    override fun apply(t: Observable<out Throwable>): Observable<*> {
        TODO("Not yet implemented")
    }
}