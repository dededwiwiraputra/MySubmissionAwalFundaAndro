package com.example.mysubmissionawal.model

import com.google.gson.annotations.SerializedName

data class GithubResponse(

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<ItemsItem>

)

data class ItemsItem(

    @field:SerializedName("gists_url")
    val gistsUrl: String,

    @field:SerializedName("repos_url")
    val reposUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("starred_url")
    val starredUrl: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String,

    @field:SerializedName("score")
    val score: Int,

    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String,

    @field:SerializedName("events_url")
    val eventsUrl: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,

    @field:SerializedName("site_admin")
    val siteAdmin: Boolean,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("gravatar_id")
    val gravatarId: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("node_id")
    val nodeId: String,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String,

    @field:SerializedName("name")
    val name: String
)

data class DetailUsers(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("followers")
    val followers: String? = null,

    @field:SerializedName("following")
    val following: String? = null
)

data class Follow(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)

data class Favorite(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)
