package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.AllTodoResponseBody
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.TodoResponseBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/15 5:18 PM
 * @description:
 */
interface TodoContract {

    interface View : IView {
        fun showNoTodoList(data : TodoResponseBody)

        fun showDeleteSuccess(success : Boolean)

        fun showUpdateSuccess(success: Boolean)
    }

    interface Presenter : IPresenter<View> {
        fun getAllTodoList(type : Int)

        fun getNoTodoList(page : Int, type: Int)

        fun getDoneList(page: Int, type: Int)

        fun deleteTodoById(id : Int)

        fun updateTodoById(id: Int, status : Int)
    }

    interface Model : IModel {

        fun getTodoList(type: Int) : Observable<HttpResult<AllTodoResponseBody>>

        fun getNoTodoList(page: Int, type: Int) : Observable<HttpResult<TodoResponseBody>>

        fun getDoneList(page : Int, type: Int) : Observable<HttpResult<TodoResponseBody>>

        fun deleteTodoById(id : Int) : Observable<HttpResult<Any>>

        fun updateTodoById(id: Int, status: Int) : Observable<HttpResult<Any>>
    }

}