package com.cgy.wanandroid.mvp.contract

/**
 * @author: cgy
 * @date: 2021/2/7 3:34 PM
 * @description:
 */
interface ContentContract {

    interface View : CommonContract.View {}

    interface Presenter : CommonContract.Presenter<View> {

    }

    interface Model : CommonContract.Model {

    }
}