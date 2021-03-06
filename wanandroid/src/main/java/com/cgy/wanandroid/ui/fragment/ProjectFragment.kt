package com.cgy.wanandroid.ui.fragment

import android.support.design.widget.TabLayout
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.ProjectPagerAdapter
import com.cgy.wanandroid.base.BaseMvpFragment
import com.cgy.wanandroid.event.ColorEvent
import com.cgy.wanandroid.mvp.contract.ProjectContract
import com.cgy.wanandroid.mvp.model.bean.ProjectTreeBean
import com.cgy.wanandroid.mvp.presenter.ProjectPresenter
import com.cgy.wanandroid.utils.SettingUtil
import kotlinx.android.synthetic.main.fragment_project.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author: cgy
 * @date: 2021/2/4 10:44 AM
 * @description: 项目模块
 */
class ProjectFragment : BaseMvpFragment<ProjectContract.View, ProjectContract.Presenter> (), ProjectContract.View {

    companion object {
        fun getInstance() : ProjectFragment = ProjectFragment()
    }

    override fun createPresenter(): ProjectContract.Presenter  = ProjectPresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_project

    /**
     * ProjectTreeBean
     */
    private var projectTree = mutableListOf<ProjectTreeBean>()

    /**
     * ViewPagerAdapter
     */
    private val viewPagerAdapter: ProjectPagerAdapter by lazy {
        ProjectPagerAdapter(projectTree, childFragmentManager)
    }

    override fun useEventBus(): Boolean  = true

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        viewPager.run {
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        }
        tabLayout.run {
            setupWithViewPager(viewPager)
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
            addOnTabSelectedListener(onTabSelectedListener)
        }

        refreshColor(ColorEvent(true))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshColor(event: ColorEvent) {
        if (event.isRefresh) {
            if (!SettingUtil.getIsNightMode()) {
                tabLayout.setBackgroundColor(SettingUtil.getColor())
            }
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
    }

    override fun lazyLoad() {
        mPresenter?.requestProjectTree()
    }

    override fun doReConnected() {
        if (projectTree.size == 0) {
            super.doReConnected()
        }
    }

    override fun setProjectTree(list: List<ProjectTreeBean>) {
        list.let {
            projectTree.addAll(it)
            doAsync {
                Thread.sleep(10)
                uiThread {
                    viewPager.run {
                        adapter = viewPagerAdapter
                        offscreenPageLimit = projectTree.size
                    }
                }
            }
        }
        if (list.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    /**
     * onTabSelectedListener
     */
    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
            tab?.let {
                viewPager.setCurrentItem(it.position, false)
            }
        }

    }

    override fun scrollToTop() {
        if (viewPagerAdapter.count == 0) {
            return
        }
        val fragment : ProjectListFragment = viewPagerAdapter.getItem(viewPager.currentItem) as ProjectListFragment
        fragment.scrollToTop()
    }

}