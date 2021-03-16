package com.cgy.wanandroid.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.adapter.TodoAdapter
import com.cgy.wanandroid.app.App
import com.cgy.wanandroid.base.BaseMvpListFragment
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.event.RefreshTodoEvent
import com.cgy.wanandroid.event.TodoEvent
import com.cgy.wanandroid.event.TodoTypeEvent
import com.cgy.wanandroid.ext.showSnackMsg
import com.cgy.wanandroid.mvp.contract.TodoContract
import com.cgy.wanandroid.mvp.model.bean.TodoDataBean
import com.cgy.wanandroid.mvp.model.bean.TodoResponseBody
import com.cgy.wanandroid.mvp.presenter.TodoPresenter
import com.cgy.wanandroid.ui.activity.CommonActivity
import com.cgy.wanandroid.utils.DialogUtil
import com.cgy.wanandroid.utils.NetWorkUtil
import com.cgy.wanandroid.widget.SwipeItemLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_layout.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author: cgy
 * @date: 2021/3/15 2:28 PM
 * @description:
 */
class TodoFragment : BaseMvpListFragment<TodoContract.View, TodoContract.Presenter>(),
    TodoContract.View {

    companion object {
        fun getInstance(type: Int): TodoFragment {
            val fragment = TodoFragment()
            val bundle = Bundle()
            bundle.putInt(Constant.TODO_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mType: Int = 0

    /**
     * 是否已完成 false->待办 true->已完成
     */
    private var bDone: Boolean = false

    private val datas = mutableListOf<TodoDataBean>()

    private val mAdapter: TodoAdapter by lazy {
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

    override fun createPresenter(): TodoContract.Presenter = TodoPresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_todo

    override fun initView(view: View) {
        super.initView(view)

        mType = arguments?.getInt(Constant.TODO_TYPE) ?: 0

        recyclerView.run {
            adapter = mAdapter
            addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(activity))
        }

        mAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@TodoFragment.onItemClickListener
            onItemChildClickListener = this@TodoFragment.onItemChildClickListener
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        if (bDone) {
            mPresenter?.getDoneList(1, mType)
        } else {
            mPresenter?.getNoTodoList(1, mType)
        }
    }

    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        lazyLoad()
    }

    override fun onLoadMoreList() {
        val page = mAdapter.data.size / pageSize + 1
        if (bDone) {
            mPresenter?.getDoneList(page, mType)
        } else {
            mPresenter?.getNoTodoList(page, mType)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun doTodoTypeEvent(event: TodoTypeEvent) {
        mType = event.type
        bDone = false
        lazyLoad()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun doTodoEvent(event: TodoEvent) {
        if (mType == event.curIndex) {
            when (event.type) {
                Constant.TODO_ADD -> {
                    Intent(activity, CommonActivity::class.java).run {
                        putExtra(Constant.TYPE_KEY, Constant.Type.ADD_TODO_TYPE_KEY)
                        putExtra(Constant.TODO_TYPE, mType)
                        startActivity(this)
                    }
                }
                Constant.TODO_NO -> {
                    bDone = false
                    lazyLoad()
                }
                Constant.TODO_DONE -> {
                    bDone = true
                    lazyLoad()
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun doRefresh(event: RefreshTodoEvent) {
        if (event.isRefresh) {
            if (mType == event.type) {
                lazyLoad()
            }
        }
    }

    override fun showNoTodoList(data: TodoResponseBody) {
        //TODO 待优化
        val list = mutableListOf<TodoDataBean>()
        var bHeader = true
        data.datas.forEach { todoBean ->
            bHeader = true
            for (i in list.indices) {
                if (todoBean.dateStr == list[i].header) {
                    bHeader = false
                    break
                }
            }
            if (bHeader)
                list.add(TodoDataBean(true, todoBean.dateStr))
            list.add(TodoDataBean(todoBean))
        }

        list.let {
            mAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                pageSize = data.size
                if (data.over) {
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

    override fun showDeleteSuccess(success: Boolean) {
        if (success) {
            showMsg(resources.getString(R.string.delete_success))
        }
    }

    override fun showUpdateSuccess(success: Boolean) {
        if (success) {
            showMsg(resources.getString(R.string.completed))
        }
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
        }
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            if (datas.size != 0) {
                val data = datas[position].t
                when (view.id) {
                    R.id.btn_delete -> {
                        if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                            showSnackMsg(resources.getString(R.string.no_network))
                            return@OnItemChildClickListener
                        }
                        activity?.let {
                            DialogUtil.getConfirmDialog(it,
                                resources.getString(R.string.confirm_delete),
                                DialogInterface.OnClickListener { _, _ ->
                                    mPresenter?.deleteTodoById(data.id)
                                    mAdapter.remove(position)
                                }).show()
                        }
                    }
                    R.id.btn_done -> {
                        if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                            showSnackMsg(resources.getString(R.string.no_network))
                            return@OnItemChildClickListener
                        }
                        if (bDone) {
                            mPresenter?.updateTodoById(data.id, 0)
                        } else {
                            mPresenter?.updateTodoById(data.id, 1)
                        }
                        mAdapter.remove(position)
                    }
                    R.id.item_todo_content -> {
                        if (bDone) {
                            Intent(activity, CommonActivity::class.java).run {
                                putExtra(Constant.TYPE_KEY, Constant.Type.SEE_TODO_TYPE_KEY)
                                putExtra(Constant.TODO_BEAN, data)
                                putExtra(Constant.TODO_TYPE, mType)
                                startActivity(this)
                            }
                        } else {
                            Intent(activity, CommonActivity::class.java).run {
                                putExtra(Constant.TYPE_KEY, Constant.Type.EDIT_TODO_TYPE_KEY)
                                putExtra(Constant.TODO_BEAN, data)
                                startActivity(this)
                            }
                        }
                    }
                }
            }
        }
}