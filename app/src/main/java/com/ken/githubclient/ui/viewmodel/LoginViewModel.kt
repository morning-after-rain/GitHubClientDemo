package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.net.RetrofitClient
import com.ken.githubclient.net.repository.LoginRepository


class LoginViewModel : BaseViewModel() {

    private val loginRepository by lazy { LoginRepository() }
    val loginStatus = MutableLiveData<LoginStates>()


    fun login(account: String, password: String) {
        launch(
            block = {
                loginStatus.value = LoginStates.REQUEST
                val userInfo = loginRepository.login()
                //todo 缓存
                loginStatus.value = LoginStates.LOGIN_SUCCESS
            },
            error = {
                println(it.message)
                loginStatus.value = LoginStates.LOGIN_FAIL
            }
        )
    }

    fun logout() {
        loginStatus.value = LoginStates.LOGIN_FAIL
        RetrofitClient.clearCookie()
    }

}

enum class LoginStates {
    REQUEST, LOGIN_SUCCESS, LOGIN_FAIL
}