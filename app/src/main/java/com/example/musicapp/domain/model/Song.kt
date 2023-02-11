package com.example.musicapp.domain.model

import com.example.musicapp.data.database.entities.MusicEntity
import com.example.musicapp.data.model.ResultSong
import com.google.gson.annotations.SerializedName

data class Song(
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackName: String,
    val trackPrice: Double,
    val previewUrl: String
)

fun ResultSong?.toDomain(): Song =
    Song(
        artistName = this?.artistName ?: " - ",
        artworkUrl60 = this?.artworkUrl60 ?: " - ",
        collectionName = this?.collectionName ?: " - ",
        trackName = this?.trackName ?: " - ",
        trackPrice = this?.trackPrice ?: 0.0,
        previewUrl = this?.previewUrl ?: " - ",
    )

fun MusicEntity?.toDomain(): Song =
    Song(
        artistName = this?.artistName ?: " - ",
        artworkUrl60 = this?.artworkUrl60 ?: " - ",
        collectionName = this?.collectionName ?: " - ",
        trackName = this?.trackName ?: " - ",
        trackPrice = this?.trackPrice ?: 0.0,
        previewUrl = this?.previewUrl ?: " - ",
    )