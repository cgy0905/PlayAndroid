package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/16 11:00 AM
 * @description:
 */
interface AddTodoContract {

    interface View : IView {

        fun showAddTodo(success : Boolean)

        fun showUpdateTodo(success: Boolean)

        fun getType() : Int
        fun getCurrentDate() : String
        fun getTitle() : String
        fun getContent() : String
        fun getStatus() : Int
        fun getItemId() : Int
        fun getPriority() : String
    }

    interface Presenter : IPresenter<View> {

        fun addTodo()

        fun updateTodo(id : Int)
    }

    interface Model : IModel {

        fun addTodo(map : MutableMap<String, Any>) : Observable<HttpResult<Any>>

        fun updateTodo(id: Int, map: MutableMap<String, Any>) : Observable<HttpResult<Any>>
    }
}