package com.example.musicapp.domain

import com.example.musicapp.data.model.RockItems
import com.example.musicapp.data.network.MusicRepository
import javax.inject.Inject

class RockUseCase @Inject constructor(private val repository: MusicRepository){
    suspend operator fun invoke(): List<RockItems> = repository.getAllRock()

}