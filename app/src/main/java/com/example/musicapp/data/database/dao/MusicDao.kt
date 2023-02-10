package com.example.musicapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicapp.data.database.entities.MusicEntity

@Dao
interface MusicDao {

    @Query("SELECT * FROM music_table ORDER BY name DESC")
    suspend fun getAllQuotes(): List<MusicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<MusicEntity>)

    @Query("DELETE FROM music_table")
    suspend fun deleteAllQuotes()
}