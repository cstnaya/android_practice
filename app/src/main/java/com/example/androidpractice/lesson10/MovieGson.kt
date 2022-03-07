package com.example.androidpractice.lesson10

import com.google.gson.annotations.SerializedName

data class MovieGson (
        val prevPageToken: String,
        val nextPageToken: String,
        @SerializedName("items") val items: List<Item>,
        @SerializedName("pageInfo") val pageInfo: PageInfo,
    ) {}

data class PageInfo (val totalResults: Int, val resultsPerPage: Int) {}

data class Item (
    val id: String,
    val snippet: Snippet,
) {}

data class Snippet (
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val resourceId: ResourceId,
) {}

data class ResourceId (val kind: String, val videoId: String) {}

data class Thumbnails (val default: Picture, val medium: Picture, val high: Picture, val standard: Picture, val maxres: Picture) {}

data class Picture (val url: String, val width: String, val height: String) {}