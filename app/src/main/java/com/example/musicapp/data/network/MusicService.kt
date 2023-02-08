package com.example.musicapp.data.network

import com.example.musicapp.data.model.ClassicItems
import com.example.musicapp.data.model.PopItems
import com.example.musicapp.data.model.RockItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicService @Inject constructor(private val api: MusicApiClient) {

    suspend fun getRock(): List<RockItems> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllRock()
            response.body() ?: emptyList()
        }
    }

    suspend fun getPop(): List<PopItems> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllPop()
            response.body() ?: emptyList()
        }
    }

    suspend fun getClassic(): List<ClassicItems> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllClassic()
            response.body() ?: emptyList()
        }
    }
}