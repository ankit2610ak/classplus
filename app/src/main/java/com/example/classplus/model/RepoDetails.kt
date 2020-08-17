package com.example.classplus.model

data class RepoDetails(
    val language: String,
    val forks: Int,
    val open_issues: Int,
    val watchers: Int,
    val description: String,
    val owner: OwnerDetails

)
