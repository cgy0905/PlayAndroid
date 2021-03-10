package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.ext.ss
import com.cgy.wanandroid.mvp.contract.ProjectListContract
import com.cgy.wanandroid.mvp.model.ProjectListModel

/**
 * @author: cgy
 * @date: 2021/3/10 12:00 PM
 * @description:
 */
class ProjectListPresenter : CommonPresenter<ProjectListContract.Model, ProjectListContract.View>(), ProjectListContract.Presenter {

    override fun createModel(): ProjectListContract.Model?  = ProjectListModel()

    override fun requestProjectList(page: Int, cid: Int) {
        mModel?.requestProjectList(page, cid)?.ss(mModel, mView, page == 1) {
            mView?.setProjectList(it.data)
        }
    }
}