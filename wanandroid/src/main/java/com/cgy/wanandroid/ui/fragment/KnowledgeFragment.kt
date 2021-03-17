package com.cgy.wanandroid.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.KnowledgeAdapter
import com.cgy.wanandroid.app.App
import com.cgy.wanandroid.base.BaseMvpListFragment
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.ext.showSnackMsg
import com.cgy.wanandroid.ext.showToast
import com.cgy.wanandroid.mvp.contract.KnowledgeContract
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.presenter.KnowledgePresenter
import com.cgy.wanandroid.ui.activity.ContentActivity
import com.cgy.wanandroid.ui.activity.LoginActivity
import com.cgy.wanandroid.utils.NetWorkUtil
import com.cgy.wanandroid.widget.SpaceItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

/**
 * @author: cgy
 * @date: 2021/3/5 11:42 AM
 * @description:
 */
class KnowledgeFragment : BaseMvpListFragment<KnowledgeContract.View, KnowledgeContract.Presenter>(), KnowledgeContract.View {

    companion object {
        fun getInstance(cid : Int) : KnowledgeFragment {
            val fragment = KnowledgeFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * cid
     */
    private var cid : Int = 0

    /**
     * datas
     */
    private val datas = mutableListOf<Article>()

    /**
     * RecyclerView Divider
     */
    private val recyclerViewItemDecoration by lazy {
        activity?.let {
            SpaceItemDecoration(it)
        }
    }

    private val mdAdapter : KnowledgeAdapter by lazy {
        KnowledgeAdapter(activity, datas)
    }

    override fun hideLoading() {
        super.hideLoading()
        if (isRefresh) {
            mdAdapter.setEnableLoadMore(true)
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        if (isRefresh) {
            mdAdapter.setEnableLoadMore(true)
        } else {
            mdAdapter.loadMoreFail()
        }
    }

    override fun createPresenter(): KnowledgeContract.Presenter = KnowledgePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_refresh_layout

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0
        swipeRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }
        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = mdAdapter
            itemAnimator = DefaultItemAnimator()
            recyclerViewItemDecoration?.let { addItemDecoration(it) }
        }

        mdAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@KnowledgeFragment.onItemClickListener
            onItemChildClickListener = this@KnowledgeFragment.onItemChildClickListener
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestKnowledgeList(0, cid)
    }

    override fun onRefreshList() {
        mdAdapter.setEnableLoadMore(false)
        mPresenter?.requestKnowledgeList(0, cid)
    }

    override fun onLoadMoreList() {
        val page = mdAdapter.data.size / pageSize
        mPresenter?.requestKnowledgeList(page, cid)
    }



    override fun setKnowledgeList(articles: ArticleResponseBody) {
        articles.datas.let {
            mdAdapter.run {
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
        if (mdAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun showCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.collect_success))
        }
    }

    override fun showCancelCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.cancel_collect_success))
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

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, view, position ->
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
                    if (isLogin) {
                        if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                            showSnackMsg(resources.getString(R.string.no_network))
                            return@OnItemChildClickListener
                        }
                        val collect = data.collect
                        data.collect = !collect
                        mdAdapter.setData(position, data)
                        if (collect) {
                            mPresenter?.cancelCollectArticle(data.id)
                        } else {
                            mPresenter?.addCollectArticle(data.id)
                        }
                    } else {
                        Intent(activity, LoginActivity::class.java).run {
                            startActivity(this)
                        }
                        showToast(resources.getString(R.string.login_tint))
                    }
                }
            }
        }
    }
}