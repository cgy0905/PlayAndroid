package com.cgy.wanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.text.Html
import com.cgy.wanandroid.mvp.model.bean.Knowledge
import com.cgy.wanandroid.ui.fragment.KnowledgeFragment

/**
 * @author: cgy
 * @date: 2021/3/11 2:07 PM
 * @description:
 */
class KnowledgePageAdapter(val list: List<Knowledge>, fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(KnowledgeFragment.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? = Html.fromHtml(list[position].name)

    override fun getItemPosition(`object`: Any): Int  = PagerAdapter.POSITION_NONE
}