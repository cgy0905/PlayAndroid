package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.mvp.contract.SystemContract
import com.cgy.wanandroid.mvp.model.SystemModel

/**
 * @author: cgy
 * @date: 2021/3/5 4:09 PM
 * @description:
 */
class SystemPresenter : BasePresenter<SystemModel, SystemContract.View>(), SystemContract.Presenter {

    override fun createModel(): SystemModel?  = SystemModel()
}