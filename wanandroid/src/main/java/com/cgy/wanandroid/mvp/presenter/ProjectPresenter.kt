package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.base.BasePresenter
import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.ProjectContract
import com.cgy.wanandroid.mvp.model.ProjectModel

/**
 * @author: cgy
 * @date: 2021/3/10 10:59 AM
 * @description:
 */
class ProjectPresenter : BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter {

    override fun createModel(): ProjectContract.Model?  = ProjectModel()

    override fun requestProjectTree() {
        mModel?.requestProjectTree()?.ss(mModel, mView) {
            mView?.setProjectTree(it.data)
        }
    }
}