package com.cgy.wanandroid.ui.activity

import android.content.Intent
import android.view.View
import com.cgy.wanandroid.R
import com.cgy.wanandroid.base.BaseMvpActivity
import com.cgy.wanandroid.constant.Constant
import com.cgy.wanandroid.event.LoginEvent
import com.cgy.wanandroid.ext.showToast
import com.cgy.wanandroid.mvp.model.bean.LoginData
import com.cgy.wanandroid.mvp.contract.RegisterContract
import com.cgy.wanandroid.mvp.presenter.RegisterPresenter
import com.cgy.wanandroid.utils.DialogUtil
import com.cgy.wanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(),
    RegisterContract.View {

    /**
     * local username
     */
    private var user: String by Preference(Constant.USERNAME_KEY, "")

    /**
     * local password
     */
    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

    private val mDialog by lazy {
        DialogUtil.getWaitDialog(this, getString(R.string.register_ing))
    }

    override fun createPresenter(): RegisterContract.Presenter = RegisterPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_register

    override fun useEventBus(): Boolean = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {

    }

    override fun start() {

    }

    override fun initView() {
        super.initView()

        toolbar.run {
            title = getString(R.string.register)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        btn_register.setOnClickListener(onClickListener)
        tv_sign_in.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btn_register -> {
                register()
            }
            R.id.tv_sign_in -> {
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    /**
     * 注册
     */
    private fun register() {
        if (validate()) {
            mPresenter?.register(
                et_username.text.toString(),
                et_password.text.toString(),
                et_confirm_pwd.text.toString()
            )
        }
    }

    /**
     * check data
     */
    private fun validate() : Boolean {
        var valid = true
        val username : String = et_username.text.toString()
        val password : String = et_password.text.toString()
        val confirmPwd : String = et_confirm_pwd.text.toString()
        if (username.isEmpty()) {
            et_username.error = getString(R.string.username_not_empty)
            valid = false
        }
        if (password.isEmpty()) {
            et_password.error = getString(R.string.password_not_empty)
            valid = false
        }
        if (confirmPwd.isEmpty()) {
            et_confirm_pwd.error = getString(R.string.confirm_password_not_empty)
            valid = false
        }

        if (password != confirmPwd) {
            et_confirm_pwd.error = getString(R.string.password_cannot_match)
            valid = false
        }
        return valid
    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun registerSuccess(data: LoginData) {
        showToast(getString(R.string.register_success))
        isLogin = true
        user = data.username
        pwd = data.password

        EventBus.getDefault().post(LoginEvent(true))
        finish()
    }

    override fun registerFail() {
        isLogin = false
    }
}