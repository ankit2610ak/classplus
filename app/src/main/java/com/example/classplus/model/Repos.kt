package com.example.classplus.model

data class Repos(
    val watchers: Int,
    val full_name: String,
    val name: String,
    val owner: OwnerDetails,
    val default_branch: String

)