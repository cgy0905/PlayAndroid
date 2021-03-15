package com.cgy.wanandroid.ui.fragment

import android.os.Bundle
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.TodoAdapter
import com.cgy.wanandroid.base.BaseMvpListFragment
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.mvp.contract.TodoContract
import com.cgy.wanandroid.mvp.model.bean.TodoDataBean
import com.cgy.wanandroid.mvp.model.bean.TodoResponseBody

/**
 * @author: cgy
 * @date: 2021/3/15 2:28 PM
 * @description:
 */
class TodoFragment : BaseMvpListFragment<TodoContract.View, TodoContract.Presenter>(), TodoContract.View {

    companion object {
        fun getInstance(type : Int) : TodoFragment {
            val fragment = TodoFragment()
            val bundle = Bundle()
            bundle.putInt(Constant.TODO_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mType : Int = 0

    /**
     * 是否已完成 false->待办 true->已完成
     */
    private var bDone : Boolean = false

    private val datas = mutableListOf<TodoDataBean>()

    private val mAdapter : TodoAdapter by lazy {
        TodoAdapter(R.layout.item_todo_list, R.layout.item_sticky_header, datas)
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

    override fun createPresenter(): TodoContract.Presenter {
        TODO("Not yet implemented")
    }

    override fun attachLayoutRes(): Int  = R.layout.fragment_todo

    override fun onRefreshList() {
        TODO("Not yet implemented")
    }

    override fun onLoadMoreList() {
        TODO("Not yet implemented")
    }



    override fun lazyLoad() {
        TODO("Not yet implemented")
    }

    override fun showNoTodoList(data: TodoResponseBody) {
        TODO("Not yet implemented")
    }

    override fun showDeleteSuccess(success: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showUpdateSuccess(success: Boolean) {
        TODO("Not yet implemented")
    }
}