package com.cgy.wanandroid.mvp.contract

import com.cgy.wanandroid.base.IModel
import com.cgy.wanandroid.base.IPresenter
import com.cgy.wanandroid.base.IView

/**
 * @author: cgy
 * @date: 2021/3/5 4:08 PM
 * @description:
 */
interface SystemContract {

    interface View : IView {
        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {

    }

    interface Model : IModel {

    }
}