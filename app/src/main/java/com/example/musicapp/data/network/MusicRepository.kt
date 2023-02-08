package com.example.musicapp.data.network

import com.example.musicapp.data.model.ClassicItems
import com.example.musicapp.data.model.MusicProvider
import com.example.musicapp.data.model.PopItems
import com.example.musicapp.data.model.RockItems
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val api: MusicService,
    private val provider: MusicProvider
) {

    suspend fun getAllRock(): List<RockItems> {
        val response: List<RockItems> = api.getRock()
        provider.rock = response
        return response
    }

    suspend fun getAllClassic(): List<ClassicItems> {
        val response: List<ClassicItems> = api.getClassic()
        provider.classic = response
        return response
    }

    suspend fun getAllPop(): List<PopItems> {
        val response: List<PopItems> = api.getPop()
        provider.pop = response
        return response
    }
}