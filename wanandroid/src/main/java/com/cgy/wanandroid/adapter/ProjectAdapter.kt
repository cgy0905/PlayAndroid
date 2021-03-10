package com.cgy.wanandroid.adapter

import android.content.Context
import android.text.Html
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.utils.ImageLoader
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/10 12:04 PM
 * @description:
 */
class ProjectAdapter(private val context: Context?, datas: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_project_list, datas) {

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        helper ?: return
        item ?: return
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_project_title, Html.fromHtml(item.title))
            .setText(R.id.tv_project_content, Html.fromHtml(item.desc))
            .setText(R.id.tv_project_time, item.niceDate)
            .setText(R.id.tv_project_author, authorStr)
            .setImageResource(
                R.id.iv_project_like,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
            )
            .addOnClickListener(R.id.iv_project_like)
        context.let {
            ImageLoader.load(it, item.envelopePic, helper.getView(R.id.iv_project))
        }
    }
}