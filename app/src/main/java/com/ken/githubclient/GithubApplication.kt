package com.ken.githubclient

import android.app.Application
import com.ken.githubclient.utls.CoilHelper
import com.ken.githubclient.utls.isMainProcess

class GithubApplication : Application() {
    companion object {
        lateinit var instance: GithubApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // 主进程初始化
        if (isMainProcess(this)) {
            handleInit()
        }
    }

    private fun handleInit() {
        CoilHelper.init(this)
    }
}