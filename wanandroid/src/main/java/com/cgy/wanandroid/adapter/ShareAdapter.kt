package com.cgy.wanandroid.adapter

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.utils.ImageLoader
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/15 10:20 AM
 * @description:
 */
class ShareAdapter(datas : MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_share_list, datas){

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        item ?: return
        helper ?: return
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(R.id.iv_like, if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not)
            .addOnClickListener(R.id.iv_like)
            .addOnClickListener(R.id.btn_delete)
            .addOnClickListener(R.id.rl_content)
        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
            mContext?.let {
                ImageLoader.load(it, item.envelopePic, helper.getView(R.id.iv_article_thumbnail))
            }
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
        }
        val tvFresh = helper.getView<TextView>(R.id.tv_article_fresh)
        if (item.fresh) {
            tvFresh.visibility = View.VISIBLE
        } else {
            tvFresh.visibility = View.GONE
        }
        val tvTop = helper.getView<TextView>(R.id.tv_article_top)
        if (item.top == "1") {
            tvTop.visibility = View.VISIBLE
        } else {
            tvTop.visibility = View.GONE
        }
        val tvArticleTag = helper.getView<TextView>(R.id.tv_article_tag)
        if (item.tags.size > 0) {
            tvArticleTag.visibility = View.VISIBLE
            tvArticleTag.text = item.tags[0].name
        } else {
            tvArticleTag.visibility = View.GONE
        }
        val tvArticleAudit = helper.getView<TextView>(R.id.tv_article_audit)
        if (item.audit == 0) {
            tvArticleAudit.visibility = View.VISIBLE
            tvArticleAudit.text = mContext.getString(R.string.audited)
        } else {
            tvArticleAudit.visibility = View.GONE
        }
    }
}