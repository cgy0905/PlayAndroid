package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.SquareContract
import com.cgy.wanandroid.mvp.model.SquareModel

/**
 * @author: cgy
 * @date: 2021/3/3 6:04 PM
 * @description:
 */
class SquarePresenter : CommonPresenter<SquareModel, SquareContract.View>(), SquareContract.Presenter {

    override fun createModel(): SquareModel?  = SquareModel()

    override fun getSquareList(page: Int) {
        mModel?.getSquareList(page)?.ss(mModel, mView, page == 0) {
            mView?.showSquareList(it.data)
        }
    }
}