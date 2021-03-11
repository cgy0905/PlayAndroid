package com.cgy.wanandroid.adapter

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.CollectionArticle
import com.cgy.wanandroid.utils.ImageLoader
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/11 10:50 AM
 * @description:
 */
class CollectAdapter(private val context: Context?, datas: MutableList<CollectionArticle>) :
    BaseQuickAdapter<CollectionArticle, BaseViewHolder>(R.layout.item_collect_list, datas) {

    override fun convert(helper: BaseViewHolder?, item: CollectionArticle?) {
        helper ?: return
        item ?: return

        val authorStr = when {
            item.author.isNotEmpty() -> item.author
            else -> mContext.getString(R.string.anonymous)
        }

        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(R.id.iv_like, R.drawable.ic_like)
            .addOnClickListener(R.id.iv_like)
        helper.setText(R.id.tv_article_chapterName, item.chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
            context?.let {
                ImageLoader.load(it, item.envelopePic, helper.getView(R.id.iv_article_thumbnail))
            }
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.GONE
        }
    }
}