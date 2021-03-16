package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.AddTodoContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/16 11:13 AM
 * @description:
 */
class AddTodoModel : BaseModel(), AddTodoContract.Model {

    override fun addTodo(map: MutableMap<String, Any>): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addTodo(map)
    }

    override fun updateTodo(id: Int, map: MutableMap<String, Any>): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.updateTodo(id, map)
    }
}