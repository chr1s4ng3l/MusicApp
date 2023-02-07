package com.example.musicapp.domain

import com.example.musicapp.data.model.ClassicItems
import com.example.musicapp.data.network.MusicRepository
import javax.inject.Inject

class ClassicUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(): List<ClassicItems> = repository.getAllClassic()

}