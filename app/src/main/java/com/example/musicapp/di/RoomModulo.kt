package com.example.musicapp.di

import android.content.Context
import androidx.room.Room
import com.example.musicapp.data.database.MusicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModulo {

    //Database here

    private const val MUSIC_DATABASE_NAME = "music_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MusicDatabase::class.java, MUSIC_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMusicDao(db: MusicDatabase) = db.getSongDao()
}