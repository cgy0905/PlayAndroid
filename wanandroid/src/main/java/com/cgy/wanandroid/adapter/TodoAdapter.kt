package com.cgy.wanandroid.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.mvp.model.bean.TodoBean
import com.cgy.wanandroid.mvp.model.bean.TodoDataBean
import com.cgy.wanandroid.widget.TiltTextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class TodoAdapter : BaseSectionQuickAdapter<TodoDataBean, BaseViewHolder> {

    constructor(layoutId: Int, sectionHeadResId: Int, data: MutableList<TodoDataBean>) : super(
        layoutId,
        sectionHeadResId,
        data
    )

    override fun convertHead(helper: BaseViewHolder?, item: TodoDataBean?) {
        helper ?: return
        item ?: return
        helper.setText(R.id.tv_header, item.header)
    }

    override fun convert(helper: BaseViewHolder?, item: TodoDataBean?) {
        helper ?: return
        item ?: return
        val itemData = item.t as TodoBean
        helper.setText(R.id.tv_todo_title, itemData.title)
            .addOnClickListener(R.id.btn_delete)
            .addOnClickListener(R.id.btn_done)
            .addOnClickListener(R.id.item_todo_content)
        val tvTodoDesc = helper.getView<TextView>(R.id.tv_todo_desc)
        tvTodoDesc.text = ""
        tvTodoDesc.visibility = View.VISIBLE
        if (itemData.content.isNotEmpty()) {
            tvTodoDesc.visibility = View.VISIBLE
            tvTodoDesc.text = itemData.content
        }
        val btnDone = helper.getView<Button>(R.id.btn_done)
        if (itemData.status == 0) {
            btnDone.text = mContext.resources.getString(R.string.mark_done)
        } else if (itemData.status == 1) {
            btnDone.text = mContext.resources.getString(R.string.restore)
        }
        val tvTilt = helper.getView<TiltTextView>(R.id.tv_tilt)
        if (itemData.priority == 1) {
            tvTilt.setText(mContext.resources.getString(R.string.priority_1))
            tvTilt.visibility = View.VISIBLE
        } else {
            tvTilt.visibility = View.GONE
        }
    }

}
