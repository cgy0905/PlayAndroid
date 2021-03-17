package com.cgy.wanandroid.adapter

import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.CoinInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/11 6:12 PM
 * @description:
 */
class RankAdapter : BaseQuickAdapter<CoinInfoBean, BaseViewHolder>(R.layout.item_rank_list){

    override fun convert(helper: BaseViewHolder?, item: CoinInfoBean?) {
        helper ?: return
        item ?: return

        val index = helper.layoutPosition
        helper.setText(R.id.tv_username, item.username)
            .setText(R.id.tv_score, item.coinCount.toString())
            .setText(R.id.tv_ranking, (index + 1).toString())
    }
}