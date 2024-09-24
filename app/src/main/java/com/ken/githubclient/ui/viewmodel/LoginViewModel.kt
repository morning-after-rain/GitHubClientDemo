package com.ken.githubclient.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ken.githubclient.net.RetrofitClient
import com.ken.githubclient.net.repository.LoginRepository


class LoginViewModel : BaseViewModel() {

    private val loginRepository by lazy { LoginRepository() }
    val submitting = MutableLiveData<Boolean>()
    val loginResult = MutableLiveData<Boolean>()
    val loginStatus = MutableLiveData<Boolean>();


    fun login(account: String, password: String) {
        launch(
            block = {
                submitting.value = true
                val userInfo = loginRepository.login()
                //todo 缓存
                submitting.value = false
                loginResult.value = true
                loginStatus.value = true
            },
            error = {
                println(it.message)
                submitting.value = false
                loginResult.value = false
                loginStatus.value = false
            }
        )
    }

    fun logout() {
        loginStatus.value = false
        RetrofitClient.clearCookie()
    }

}