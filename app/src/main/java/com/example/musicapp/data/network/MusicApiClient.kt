package com.example.musicapp.data.network

import com.example.musicapp.data.model.GenreEnum
import com.example.musicapp.data.model.MusicItems
import com.example.musicapp.data.model.MusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApiClient {

    /**
     * method to get the ROCK from server
     */
    @GET(PATH)
    suspend fun getAllSongs(@Query("term") sogGenre: GenreEnum): Response<MusicItems>

    companion object {
        //https://itunes.apple.com/

        const val BASE_URL = "https://itunes.apple.com/"
        const val PATH = "search"

    }
}