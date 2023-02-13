package com.example.musicapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicapp.data.database.entities.MusicEntity
import com.example.musicapp.domain.model.Song

@Dao
interface MusicDao {

    //All querys here
    @Query("SELECT * FROM music_table ORDER BY artistName DESC")
    suspend fun getAllSongs(): List<MusicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(song: List<MusicEntity>)

    @Query("DELETE FROM music_table")
    suspend fun deleteAllSongs()
}