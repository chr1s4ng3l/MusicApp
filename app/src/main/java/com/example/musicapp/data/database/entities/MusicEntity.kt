package com.example.musicapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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


