package com.example.musicapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicProvider @Inject constructor() {
    var classic: List<ClassicItems> = emptyList()
    var pop: List<PopItems> = emptyList()
    var rock: List<RockItems> = emptyList()
}
