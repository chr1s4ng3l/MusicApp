package com.example.musicapp.domain

import com.example.musicapp.data.model.GenreEnum
import com.example.musicapp.data.network.MusicRepository
import com.example.musicapp.domain.model.Song
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetSongUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    private val genreEnum: GenreEnum
) {

}