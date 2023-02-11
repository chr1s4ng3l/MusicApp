package com.example.musicapp.data.model


import com.google.gson.annotations.SerializedName

data class MusicItems(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<ResultSong>? = null
)