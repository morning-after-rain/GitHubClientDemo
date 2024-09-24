package com.ken.githubclient.utls

import android.util.Log

object Logger {
    private const val defaultTag = "GithubClient"
    private const val nullStr = "__NULL__"
    private const val isDeug = true;

    @JvmStatic
    @JvmOverloads
    fun d(tag: String? = null, msg: String?) {
        if (isDeug) {
            Log.d(tag ?: defaultTag, msg ?: nullStr)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun i(tag: String? = null, msg: String?) {
        if (isDeug) {
            Log.i(tag ?: defaultTag, msg ?: nullStr)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun e(tag: String? = null, msg: String?) {
        if (isDeug) {
            Log.e(tag ?: defaultTag, msg ?: nullStr)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun w(tag: String? = null, msg: String?) {
        if (isDeug) {
            Log.w(tag ?: defaultTag, msg ?: nullStr)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun v(tag: String? = null, msg: String?) {
        if (isDeug) {
            Log.v(tag ?: defaultTag, msg ?: nullStr)
        }
    }

}