package com.example.musicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicapp.data.database.dao.MusicDao
import com.example.musicapp.data.database.entities.MusicEntity

@Database(entities = [MusicEntity::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getSongDao(): MusicDao


}