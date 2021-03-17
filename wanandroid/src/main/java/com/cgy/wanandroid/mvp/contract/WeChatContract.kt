package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 11:16 AM
 * @description:
 */
interface WeChatContract {

    interface View : IView {

        fun scrollToTop()

        fun showWXChapters(chapters : MutableList<WXChapterBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getWXChapters()
    }

    interface Model : IModel {
        fun getWXChapters() : Observable<HttpResult<MutableList<WXChapterBean>>>
    }
}