package com.example.classplus.dataSource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.classplus.model.UserDetails
import com.example.classplus.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Response

class UserDataSource() : PageKeyedDataSource<Int, UserDetails>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserDetails>
    ) {

        val service = ApiClient.getClient
        val call = service.getUserDetails(FIRST_PAGE)
        call.enqueue(object : retrofit2.Callback<ArrayList<UserDetails>> {
            override fun onFailure(call: Call<ArrayList<UserDetails>>, t: Throwable) {
                Log.d("MainActivityViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<UserDetails>>,
                response: Response<ArrayList<UserDetails>>
            ) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    userList?.let {
                        callback.onResult(userList, null, FIRST_PAGE + 1)

                    }
                }
            }
        })
    }


    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserDetails>
    ) {
        val service = ApiClient.getClient
        val call = service.getUserDetails(params.key)
        call.enqueue(object : retrofit2.Callback<ArrayList<UserDetails>> {
            override fun onFailure(call: Call<ArrayList<UserDetails>>, t: Throwable) {
                Log.d("MainActivityViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<UserDetails>>,
                response: Response<ArrayList<UserDetails>>
            ) {
                if (response.isSuccessful) {
                    val userList = response.body()

                    val key = params.key + 1

                    userList?.let {
                        callback.onResult(userList, key)

                    }
                }
            }
        })
    }


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserDetails>
    ) {
        val service = ApiClient.getClient
        val call = service.getUserDetails(params.key)
        call.enqueue(object : retrofit2.Callback<ArrayList<UserDetails>> {
            override fun onFailure(call: Call<ArrayList<UserDetails>>, t: Throwable) {
                Log.d("MainActivityViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<UserDetails>>,
                response: Response<ArrayList<UserDetails>>
            ) {
                if (response.isSuccessful) {
                    val userList = response.body()

                    val key = if (params.key > 1) params.key - 1 else 0

                    userList?.let {
                        callback.onResult(userList, key)

                    }
                }
            }
        })
    }


    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }
}