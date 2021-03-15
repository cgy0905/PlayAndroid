package com.cgy.wanandroid.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author: cgy
 * @date: 2021/3/15 5:51 PM
 * @description:
 */
class TodoDataBean : SectionEntity<TodoBean>{

    constructor(isHeader : Boolean, headerName : String) : super(isHeader, headerName)

    constructor(todoBean: TodoBean) : super(todoBean)
}