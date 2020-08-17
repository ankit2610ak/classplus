package com.example.classplus.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.classplus.model.Repos
import com.example.classplus.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Response

class RepoViewModel : ViewModel() {
    private var liveData: MutableLiveData<ArrayList<Repos>> = MutableLiveData()
    val _livedata: LiveData<ArrayList<Repos>>
        get() = liveData

    fun getRepoDetails( login: String) {
        val call: Call<ArrayList<Repos>> = ApiClient.getClient.getRepos(login)
        call.enqueue(object : retrofit2.Callback<ArrayList<Repos>> {
            override fun onFailure(call: Call<ArrayList<Repos>>, t: Throwable) {
                Log.d("RepoViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Repos>>,
                response: Response<ArrayList<Repos>>
            ) {
                val repoList = response.body()
                liveData.postValue(repoList)
            }

        })
    }
}