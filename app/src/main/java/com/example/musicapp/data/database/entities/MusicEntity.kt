package com.example.musicapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.data.model.ResultSong
import com.example.musicapp.domain.model.Song

@Entity(tableName = "music_table")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "artworkUrl60") val artworkUrl60: String,
    @ColumnInfo(name = "collectionName") val collectionName: String,
    @ColumnInfo(name = "trackName") val trackName: String,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double,
    @ColumnInfo(name = "previewUrl") val previewUrl: String
)

fun Song.toDatabase() = MusicEntity(
    artistName = artistName,
    artworkUrl60 = artworkUrl60,
    collectionName = collectionName,
    trackName = trackName,
    trackPrice = trackPrice,
    previewUrl = previewUrl
)



