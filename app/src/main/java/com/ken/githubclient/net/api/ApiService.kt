package com.ken.githubclient.net.api

import com.ken.githubclient.net.model.FollowersEntity
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.model.SearchRepoResult
import com.ken.githubclient.net.model.SearchUserResult
import com.ken.githubclient.net.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    /**
     * 认证
     */
    @GET("user")
    suspend fun fetchUserOwner(@Header("Authorization") authorization: String): UserInfo


    /**
     * 搜索仓库
     */
    @GET("search/repositories")
    suspend fun searchRepos(
        @Query(value = "q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): SearchRepoResult

    /**
     * 搜索用户
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query(value = "q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchUserResult

    /**
     * 获取用户集合
     */
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int, @Query("per_page") perPage: Int
    ): MutableList<UserInfo>

    /**
     * 获取用户仓库
     */
    @GET("/users/{username}/repos")
    suspend fun getPros(@Path("username") username: String): MutableList<RepoEntity>

    /**
     * 获取用户following
     */
    @GET("/users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): MutableList<FollowersEntity>

    /**
     * 获取用户followers
     */
    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): MutableList<FollowersEntity>

}