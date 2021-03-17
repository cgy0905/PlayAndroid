package com.cgy.wanandroid.adapter

import android.content.Context
import android.text.Html
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.KnowledgeTreeBody
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/5 4:33 PM
 * @description:
 */
class KnowledgeTreeAdapter(private val context: Context?, datas: MutableList<KnowledgeTreeBody>) :
    BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.item_knowledge_tree_list, datas) {

    override fun convert(helper: BaseViewHolder?, item: KnowledgeTreeBody?) {
        helper?.setText(R.id.title_first, item?.name)
        item?.children.let {
            helper?.setText(
                R.id.title_second,
                it?.joinToString("    ", transform = { child ->
                    Html.fromHtml(child.name)
                })
            )
        }
    }
}