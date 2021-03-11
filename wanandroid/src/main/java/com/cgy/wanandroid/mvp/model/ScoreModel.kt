package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.ScoreContract
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.UserScoreBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/11 3:11 PM
 * @description:
 */
class ScoreModel : BaseModel(), ScoreContract.Model {

    override fun getUserScoreList(page: Int): Observable<HttpResult<BaseListResponseBody<UserScoreBean>>> {
        return RetrofitHelper.service.getUserScoreList(page)
    }
}