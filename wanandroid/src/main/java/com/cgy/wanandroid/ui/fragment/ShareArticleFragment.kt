package com.cgy.wanandroid.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseMvpFragment
import com.cgy.wanandroid.event.RefreshShareEvent
import com.cgy.wanandroid.mvp.contract.ShareArticleContract
import com.cgy.wanandroid.mvp.presenter.ShareArticlePresenter
import com.cgy.wanandroid.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_share_article.*
import org.greenrobot.eventbus.EventBus

/**
 * @author: cgy
 * @date: 2021/3/5 10:27 AM
 * @description: 分享文章
 */
class ShareArticleFragment : BaseMvpFragment<ShareArticleContract.View, ShareArticlePresenter>(), ShareArticleContract.View {

    companion object {
        fun getInstance() : ShareArticleFragment = ShareArticleFragment()
    }

    private val mDialog by lazy {
        DialogUtil.getWaitDialog(activity!!, getString(R.string.submit_ing))
    }

    override fun getArticleTitle(): String = et_article_title.text.toString().trim()

    override fun getArticleLink(): String = et_article_link.text.toString().trim()

    override fun createPresenter(): ShareArticlePresenter = ShareArticlePresenter()

    override fun attachLayoutRes(): Int  = R.layout.fragment_share_article

    override fun showLoading() {
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun initView(view: View) {
        //在fragment中使用onCreateOptionMenu 时需要在onCreateView中添加此方法 否则不会调用
        setHasOptionsMenu(true)
        super.initView(view)
    }
    override fun lazyLoad() {

    }

    override fun showShareArticle(success: Boolean) {
        if (success) {
            showDefaultMsg(getString(R.string.share_success))
            EventBus.getDefault().post(RefreshShareEvent(true))
            activity?.finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_share_article, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share_article -> {
                mPresenter?.shareArticle()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}