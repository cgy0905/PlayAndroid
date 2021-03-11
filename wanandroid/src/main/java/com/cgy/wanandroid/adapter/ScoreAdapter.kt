package com.cgy.wanandroid.adapter

import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.UserScoreBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/11 3:31 PM
 * @description:
 */
class ScoreAdapter : BaseQuickAdapter<UserScoreBean, BaseViewHolder>(R.layout.item_score_list){

    override fun convert(helper: BaseViewHolder?, item: UserScoreBean?) {
        helper ?: return
        item ?: return
        helper.setText(R.id.tv_reason, item.reason)
            .setText(R.id.tv_desc, item.desc)
            .setText(R.id.tv_score, "+${item.coinCount}")
    }
}