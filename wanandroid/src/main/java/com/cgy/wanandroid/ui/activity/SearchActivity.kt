package com.cgy.wanandroid.ui.activity

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.SearchHistoryAdapter
import com.cgy.wanandroid.base.BaseMvpSwipeBackActivity
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.mvp.contract.SearchContract
import com.cgy.wanandroid.mvp.model.bean.HotSearchBean
import com.cgy.wanandroid.mvp.model.bean.SearchHistoryBean
import com.cgy.wanandroid.mvp.presenter.SearchPresenter
import com.cgy.wanandroid.utils.CommonUtil
import com.cgy.wanandroid.utils.DisplayManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.Exception

class SearchActivity : BaseMvpSwipeBackActivity<SearchContract.View, SearchContract.Presenter>(), SearchContract.View {


    override fun createPresenter(): SearchContract.Presenter  = SearchPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_search

    /**
     * 热搜数据
     */
    private var mHotSearchList = mutableListOf<HotSearchBean>()

    /**
     * datas
     */
    private val datas = mutableListOf<SearchHistoryBean>()

    /**
     * SearchHistoryAdapter
     */
    private val searHistoryAdapter by lazy {
        SearchHistoryAdapter(this, datas)
    }

    /**
     * LinearLayoutManager
     */
    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }


    override fun initData() {

    }

    override fun initView() {
        super.initView()
        toolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        hot_search_flow_layout.run {
            setOnTagClickListener { view, position, parent ->
                if (mHotSearchList.size > 0) {
                    val hotSearchBean = mHotSearchList[position]
                    goToSearchList(hotSearchBean.name)
                    true
                }
                false
            }
        }
        rv_history_search.run {
            layoutManager = linearLayoutManager
            adapter = searHistoryAdapter
            itemAnimator = DefaultItemAnimator()
        }

        searHistoryAdapter.run {
            bindToRecyclerView(rv_history_search)
            onItemClickListener = this@SearchActivity.onItemClickListener
            onItemChildClickListener = this@SearchActivity.onItemChildClickListener
            setEmptyView(R.layout.search_empty_view)
        }

        tv_search_history_clear_all.setOnClickListener {
            datas.clear()
            searHistoryAdapter.replaceData(datas)
            mPresenter?.clearAllHistory()
        }

        mPresenter?.getHotSearchData()

    }

    override fun onResume() {
        super.onResume()
        mPresenter?.queryHistory()
    }

    override fun start() {

    }

    private fun goToSearchList(key : String) {
        mPresenter?.saveSearchKey(key)
        Intent(this, CommonActivity::class.java).run {
            putExtra(Constant.TYPE_KEY, Constant.Type.SEARCH_TYPE_KEY)
            putExtra(Constant.SEARCH_KEY, key)
            startActivity(this)
        }
    }

    override fun showHistoryData(historyBeans: MutableList<SearchHistoryBean>) {
        searHistoryAdapter.replaceData(historyBeans)
    }

    override fun showHotSearchData(hotSearchList: MutableList<HotSearchBean>) {
        this.mHotSearchList.addAll(hotSearchList)
        hot_search_flow_layout.adapter = object : TagAdapter<HotSearchBean>(hotSearchList) {
            override fun getView(parent: FlowLayout?, position: Int, hotSearchBean: HotSearchBean?): View {
                val tv : TextView = LayoutInflater.from(parent?.context).inflate(R.layout.flow_layout_tv,
                hot_search_flow_layout, false) as TextView
                val padding : Int = DisplayManager.dip2px(10F)
                tv.setPadding(padding, padding, padding, padding)
                tv.text = hotSearchBean?.name
                tv.setTextColor(CommonUtil.randomColor())
                return tv
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.onActionViewExpanded()
        searchView.queryHint = getString(R.string.search_tint)
        searchView.setOnQueryTextListener(queryTextListener)
        searchView.isSubmitButtonEnabled = true
        try {
            val field = searchView.javaClass.getDeclaredField("mGoButton")
            field.isAccessible = true
            val mGoButton = field.get(searchView) as ImageView
            mGoButton.setImageResource(R.drawable.ic_search_white_24dp)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * OnQueryTextListener
     */
    private val queryTextListener = object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            goToSearchList(query.toString())
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener{ _, _, position ->
        if (searHistoryAdapter.data.size != 0) {
            val item = searHistoryAdapter.data[position]
            goToSearchList(item.key)
        }
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener{ _, view, position ->
            if (searHistoryAdapter.data.size != 0) {
                val item = searHistoryAdapter.data[position]
                when(view.id) {
                    R.id.iv_clear -> {
                        mPresenter?.deleteById(item.id)
                        searHistoryAdapter.remove(position)
                    }
                }
            }
        }
}