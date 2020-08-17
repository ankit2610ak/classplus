package com.example.classplus.retrofit

import com.example.classplus.model.RepoDetails
import com.example.classplus.model.Repos
import com.example.classplus.model.UserDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        private const val USER = "users?since=0"
    }

    @GET(USER)
    fun getUserDetails(
        @Query("page") page: Int
    ): Call<ArrayList<UserDetails>>

    @GET("users/{login}/repos")
    fun getRepos(
        @Path("login") login: String
    ): Call<ArrayList<Repos>>

    @GET("repos/{login}/{name}")
    fun getRepoDetails(
        @Path("login") login: String,
        @Path("name") name: String
    ): Call<RepoDetails>

}