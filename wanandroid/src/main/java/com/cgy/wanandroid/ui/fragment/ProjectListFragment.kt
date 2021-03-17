package com.cgy.wanandroid.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.ProjectAdapter
import com.cgy.wanandroid.app.App
import com.cgy.wanandroid.base.BaseMvpListFragment
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.ext.showSnackMsg
import com.cgy.wanandroid.ext.showToast
import com.cgy.wanandroid.mvp.contract.ProjectListContract
import com.cgy.wanandroid.mvp.model.bean.Article
import com.cgy.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cgy.wanandroid.mvp.presenter.ProjectListPresenter
import com.cgy.wanandroid.ui.activity.ContentActivity
import com.cgy.wanandroid.ui.activity.LoginActivity
import com.cgy.wanandroid.utils.NetWorkUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_layout.*

/**
 * @author: cgy
 * @date: 2021/3/10 11:30 AM
 * @description:
 */
class ProjectListFragment :
    BaseMvpListFragment<ProjectListContract.View, ProjectListContract.Presenter>(),
    ProjectListContract.View {

    companion object {
        fun getInstance(cid: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * cid
     */
    private var cid: Int = -1


    /**
     * Article datas
     */
    private val datas = mutableListOf<Article>()

    /**
     * ProjectAdapter
     */
    private val mAdapter: ProjectAdapter by lazy {
        ProjectAdapter(activity, datas)
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

    override fun createPresenter(): ProjectListContract.Presenter = ProjectListPresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_refresh_layout

    override fun initView(view: View) {
        super.initView(view)

        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: -1

        recyclerView.adapter = mAdapter

        mAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@ProjectListFragment.onItemClickListener
            onItemChildClickListener = this@ProjectListFragment.onItemChildClickListener
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestProjectList(1, cid)
    }

    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        mPresenter?.requestProjectList(1, cid)
    }

    override fun onLoadMoreList() {
        val page = mAdapter.data.size / pageSize + 1
        mPresenter?.requestProjectList(page, cid)
    }

    override fun setProjectList(articles: ArticleResponseBody) {
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

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
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
    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            if (datas.size != 0) {
                val data = datas[position]
                when (view.id) {
                    R.id.iv_project_like -> {
                        if (isLogin) {
                            if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                                showSnackMsg(resources.getString(R.string.no_network))
                                return@OnItemChildClickListener
                            }
                            val collect = data.collect
                            data.collect = !collect
                            mAdapter.setData(position, data)
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