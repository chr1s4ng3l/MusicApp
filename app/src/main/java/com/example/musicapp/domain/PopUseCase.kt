package com.example.musicapp.domain

import com.example.musicapp.data.model.PopItems
import com.example.musicapp.data.network.MusicRepository
import javax.inject.Inject

class PopUseCase @Inject constructor(private val repository: MusicRepository) {
    suspend operator fun invoke(): List<PopItems> = repository.getAllPop()

}