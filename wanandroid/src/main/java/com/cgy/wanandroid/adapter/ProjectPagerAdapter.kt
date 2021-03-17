package com.cgy.wanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.text.Html
import com.cgy.wanandroid.mvp.model.bean.ProjectTreeBean
import com.cgy.wanandroid.ui.fragment.ProjectListFragment

/**
 * @author: cgy
 * @date: 2021/3/10 11:27 AM
 * @description:
 */
class ProjectPagerAdapter(private val list: MutableList<ProjectTreeBean>, fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(ProjectListFragment.getInstance(it.id))
        }
    }
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? = Html.fromHtml(list[position].name)

    override fun getItemPosition(`object`: Any): Int  = PagerAdapter.POSITION_NONE
}