package com.example.classplus.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.classplus.model.UserDetails
import com.example.classplus.dataSource.UserDataSouceFactory
import com.example.classplus.dataSource.UserDataSource

class MainViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<UserDetails>>
    private var liveDataSource: LiveData<UserDataSource>

    init {
        val itemDataSourceFactory = UserDataSouceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}