package com.cgy.wanandroid.adapter

import android.app.ActivityOptions
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.mvp.model.bean.NavigationBean
import com.cgy.wanandroid.ui.activity.ContentActivity
import com.cgy.wanandroid.utils.CommonUtil
import com.cgy.wanandroid.utils.DisplayManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * @author: cgy
 * @date: 2021/3/5 5:23 PM
 * @description:
 */
class NavigationAdapter(context: Context?, datas: MutableList<NavigationBean>) :
    BaseQuickAdapter<NavigationBean, BaseViewHolder>(R.layout.item_navigation_list, datas) {


    override fun convert(helper: BaseViewHolder?, item: NavigationBean?) {
        item ?: return
        helper?.setText(R.id.tv_item_navigation, item.name)
        val flowLayout: TagFlowLayout? = helper?.getView(R.id.flow_layout_item_navigation)
        val articles: List<Article> = item.articles
        flowLayout?.run {
            adapter = object : TagAdapter<Article>(articles) {
                override fun getView(parent: FlowLayout?, position: Int, article: Article?): View? {

                    val tv: TextView = LayoutInflater.from(parent?.context).inflate(
                        R.layout.flow_layout_tv,
                        flowLayout, false
                    ) as TextView

                    article ?: return null

                    val padding: Int = DisplayManager.dip2px(10F)
                    tv.setPadding(padding, padding, padding, padding)
                    tv.text = article.title
                    tv.setTextColor(CommonUtil.randomColor())

                    setOnTagClickListener { view, position, _ ->
                        val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(
                            view,
                            view.width / 2,
                            view.height / 2,
                            0,
                            0)
                        val data : Article = articles[position]
                        ContentActivity.start(context, data.id, data.title, data.link, options.toBundle())
                        true
                    }
                    return tv
                }

            }
        }
    }
}