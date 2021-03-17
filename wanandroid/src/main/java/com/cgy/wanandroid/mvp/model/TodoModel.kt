package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.TodoContract
import com.cgy.wanandroid.mvp.model.bean.AllTodoResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.TodoResponseBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/16 9:54 AM
 * @description:
 */
class TodoModel : BaseModel(), TodoContract.Model {

    override fun getTodoList(type: Int): Observable<HttpResult<AllTodoResponseBody>> {
        return RetrofitHelper.service.getTodoList(type)
    }

    override fun getNoTodoList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>> {
        return RetrofitHelper.service.getNoTodoList(page, type)
    }

    override fun getDoneList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>> {
        return RetrofitHelper.service.getDoneList(page, type)
    }

    override fun deleteTodoById(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.deleteTodoById(id)
    }

    override fun updateTodoById(id: Int, status: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.updateTodoById(id, status)
    }
}