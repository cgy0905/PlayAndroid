package com.cgy.wanandroid.ui.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.CollectAdapter
import com.cgy.wanandroid.base.BaseMvpListFragment
import com.cgy.wanandroid.event.ColorEvent
import com.cgy.wanandroid.event.RefreshHomeEvent
import com.cgy.wanandroid.ext.showToast
import com.cgy.wanandroid.mvp.contract.CollectContract
import com.cgy.wanandroid.mvp.model.bean.BaseListResponseBody
import com.cgy.wanandroid.mvp.model.bean.CollectionArticle
import com.cgy.wanandroid.mvp.presenter.CollectPresenter
import com.cgy.wanandroid.ui.activity.ContentActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_collect.*
import kotlinx.android.synthetic.main.fragment_refresh_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author: cgy
 * @date: 2021/3/11 10:27 AM
 * @description:
 */
class CollectFragment : BaseMvpListFragment<CollectContract.View, CollectContract.Presenter>(), CollectContract.View {

    companion object {
        fun getInstance(bundle : Bundle) : CollectFragment {
            val fragment = CollectFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    /**
     * datas
     */
    private val datas = mutableListOf<CollectionArticle>()

    private val mAdapter : CollectAdapter by lazy {
        CollectAdapter(activity, datas = datas)
    }

    override fun hideLoading() {
        super.hideLoading()
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        } else {
            mAdapter.loadMoreFail()
        }
    }

    override fun createPresenter(): CollectContract.Presenter  = CollectPresenter()

    override fun attachLayoutRes(): Int  = R.layout.fragment_collect

    override fun useEventBus(): Boolean  = true

    override fun initView(view: View) {
        super.initView(view)

        recyclerView.adapter = mAdapter

        mAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@CollectFragment.onItemClickListener
            onItemChildClickListener = this@CollectFragment.onItemChildClickListener
        }

        floating_action_btn.setOnClickListener {
            scrollToTop()
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getCollectList(0)
    }

    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        mPresenter?.getCollectList(0)
    }

    override fun onLoadMoreList() {
        val page = mAdapter.data.size / pageSize
        mPresenter?.getCollectList(page)
    }

    override fun setCollectList(articles: BaseListResponseBody<CollectionArticle>) {
        articles.datas.let {
            mAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                pageSize = articles.size
                if (articles.over) {
                    loadMoreEnd(isRefresh)
                } else {
                    loadMoreComplete()
                }
            }
        }
        if (mAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun showRemoveCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.cancel_collect_success))
            EventBus.getDefault().post(RefreshHomeEvent(true))
        }
    }

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshColor(event: ColorEvent) {
        if (event.isRefresh) {
            floating_action_btn.backgroundTintList = ColorStateList.valueOf(event.color)
        }
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
            ContentActivity.start(activity, data.id, data.title, data.link)
        }
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
        if (datas.size != 0) {
            val data = datas[position]
            when (view.id) {
                R.id.iv_like -> {
                    mAdapter.remove(position)
                    mPresenter?.removeCollectArticle(data.id, data.originId)
                }
            }
        }
    }
}