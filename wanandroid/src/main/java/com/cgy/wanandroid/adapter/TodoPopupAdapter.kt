package com.cgy.wanandroid.adapter

import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.TodoTypeBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author: cgy
 * @date: 2021/3/15 2:49 PM
 * @description:
 */
class TodoPopupAdapter : BaseQuickAdapter<TodoTypeBean, BaseViewHolder>(R.layout.item_todo_popup_list){

    override fun convert(helper: BaseViewHolder?, item: TodoTypeBean?) {
        helper ?: return
        item ?: return
        val tvPopup = helper.getView<TextView>(R.id.tv_popup)
        tvPopup.text = item.name
        if (item.isSelected) {
            tvPopup.setTextColor(mContext.resources.getColor(R.color.colorAccent))
        } else {
            tvPopup.setTextColor(mContext.resources.getColor(R.color.common_color))
        }
    }
}