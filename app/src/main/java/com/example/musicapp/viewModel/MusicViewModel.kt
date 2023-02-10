package com.example.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.GenreEnum
import com.example.musicapp.data.model.MusicItems
import com.example.musicapp.data.network.MusicRepository
import com.example.musicapp.data.network.MusicRepositoryImplementation
import com.example.musicapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = "MusicViewModel"

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepositoryImplementation,
) : ViewModel() {

   var urlTrack = ""
    private val genres = GenreEnum.values()
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _classic: MutableLiveData<UIState<MusicItems>> = MutableLiveData(UIState.LOADING)
    val classic: MutableLiveData<UIState<MusicItems>> get() = _classic
    private val _rock: MutableLiveData<UIState<MusicItems>> = MutableLiveData(UIState.LOADING)
    val rock: MutableLiveData<UIState<MusicItems>> get() = _rock
    private val _pop: MutableLiveData<UIState<MusicItems>> = MutableLiveData(UIState.LOADING)
    val pop: MutableLiveData<UIState<MusicItems>> get() = _pop

    var isLoading = MutableLiveData<Boolean>()


    init {
        getAllSongs()
    }

    private fun getAllSongs() {
        isLoading.postValue(true)
        genres.forEach { g ->
            run {
                viewModelScope.launch(ioDispatcher) {
                    musicRepository.getListByType(g).collect() {
                        when (g) {
                            GenreEnum.CLASSIC -> _classic.postValue(it)
                            GenreEnum.POP -> _pop.postValue(it)
                            GenreEnum.ROCK -> _rock.postValue(it)

                        }
                    }
                }
            }
        }


    }


}