package com.cgy.wanandroid.mvp.model

import com.cgy.wanandroid.base.BaseModel
import com.cgy.wanandroid.http.RetrofitHelper
import com.cgy.wanandroid.mvp.contract.KnowledgeTreeContract
import com.cgy.wanandroid.mvp.model.bean.HttpResult
import com.cgy.wanandroid.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * @author: cgy
 * @date: 2021/3/5 4:29 PM
 * @description:
 */
class KnowledgeTreeModel : BaseModel(),KnowledgeTreeContract.Model {
    
    override fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>> {
        return RetrofitHelper.service.getKnowledgeTree()
    }
}