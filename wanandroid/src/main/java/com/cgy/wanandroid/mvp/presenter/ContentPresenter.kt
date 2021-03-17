package com.cgy.wanandroid.mvp.presenter

import com.cgy.wanandroid.mvp.contract.ContentContract
import com.cgy.wanandroid.mvp.model.ContentModel

/**
 * @author: cgy
 * @date: 2021/2/7 3:39 PM
 * @description:
 */
class ContentPresenter : CommonPresenter<ContentContract.Model, ContentContract.View>(), ContentContract.Presenter{

    override fun createModel(): ContentContract.Model?  = ContentModel()
}