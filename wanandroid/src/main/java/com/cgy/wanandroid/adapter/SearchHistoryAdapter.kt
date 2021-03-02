package com.cgy.wanandroid.adapter

import android.content.Context
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.SearchHistoryBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/2 11:58 AM
 * @description:
 */
class SearchHistoryAdapter(private val context: Context?, datas: MutableList<SearchHistoryBean>) :
    BaseQuickAdapter<SearchHistoryBean, BaseViewHolder>(R.layout.item_search_history, datas) {


    override fun convert(helper: BaseViewHolder?, item: SearchHistoryBean?) {
        helper ?: return
        item ?: return

        helper.setText(R.id.tv_search_key, item.key)
            .addOnClickListener(R.id.iv_clear)
    }
}