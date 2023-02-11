package com.example.musicapp.data.network

import android.util.Log
import com.example.musicapp.data.database.dao.MusicDao
import com.example.musicapp.data.database.entities.MusicEntity
import com.example.musicapp.data.model.GenreEnum
import com.example.musicapp.data.model.MusicItems
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.model.toDomain
import com.example.musicapp.utils.FailResponse
import com.example.musicapp.utils.NullResponse
import com.example.musicapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "MusicRepository"

interface MusicRepository {
    fun getListByType(genre: GenreEnum): Flow<UIState<MusicItems>>
}

class MusicRepositoryImplementation @Inject constructor(
    private val api: MusicApiClient,
    private val musicDao: MusicDao

) : MusicRepository {
    override fun getListByType(genre: GenreEnum): Flow<UIState<MusicItems>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getAllSongs(genre)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getListByGenre: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullResponse()
            } else
                throw FailResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getListByGenre: $e")
            emit(UIState.ERROR(e))
        }
    }

    suspend fun getListByTypeFromDatabase(): List<Song> {

        val response: List<MusicEntity> = musicDao.getAllSongs()
        return response.map { it.toDomain() }

    }
}




