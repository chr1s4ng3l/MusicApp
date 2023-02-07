package com.example.musicapp.data.network

import com.example.musicapp.data.model.ClassicItems
import com.example.musicapp.data.model.PopItems
import com.example.musicapp.data.model.RockItems
import retrofit2.Response
import retrofit2.http.GET

interface MusicApiClient {

    /**
     * method to get the ROCK from server
     */
    @GET(ROCK_PATH)
    suspend fun getAllRock(): Response<List<RockItems>>

    /**
     * method to get the POP from server
     */
    @GET(POP_PATH)
    suspend fun getAllPop(): Response<List<PopItems>>


    /**
     * method to get the CLASSIC from server
     */
    @GET(CLASSIC_PATH)
    suspend fun getAllClassic(): Response<List<ClassicItems>>


    companion object {
        //https://itunes.apple.com/


        const val BASE_URL = "https://itunes.apple.com/"
        const val CLASSIC_PATH = "search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
        const val ROCK_PATH = "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
        const val POP_PATH = "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"

    }
}