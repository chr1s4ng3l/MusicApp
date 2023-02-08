package com.example.musicapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.ClassicItems
import com.example.musicapp.data.model.PopItems
import com.example.musicapp.data.model.RockItems
import com.example.musicapp.data.network.MusicApiClient
import com.example.musicapp.domain.ClassicUseCase
import com.example.musicapp.domain.PopUseCase
import com.example.musicapp.domain.RockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(
    private val apiClient: MusicApiClient,
    private val getClassicUseCase: ClassicUseCase,
    private val getPopUseCase: PopUseCase,
    private val getRockUseCase: RockUseCase
) : ViewModel() {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO


    private val _classicModel = MutableLiveData<ClassicItems?>()
    val classicItems: LiveData<ClassicItems?> get() = _classicModel
    private val _rockModel = MutableLiveData<RockItems?>()
    val rockItems: LiveData<RockItems?> get() = _rockModel
    private val _popModel = MutableLiveData<PopItems?>()
    val popItems: LiveData<PopItems?> get() = _popModel


    fun getAllClassic() {
        viewModelScope.launch(ioDispatcher) {

            try {
                val result = getClassicUseCase()
                if (!result.isNullOrEmpty()) {
                    _classicModel.postValue(result[0])
                }
            } catch (e: Exception) {
                println(e.message)
            }


        }
    }

    fun getAllPop() {
        viewModelScope.launch(ioDispatcher) {
            val result = getPopUseCase()

            if (!result.isNullOrEmpty()) {
                for (i in 0 until result.size) {
                    _popModel.postValue(result[i])
                }

            }

        }
    }

    fun getAllRock() {
        viewModelScope.launch(ioDispatcher) {
            val result = getRockUseCase()

            if (!result.isNullOrEmpty()) {
                for (i in 0 until result.size) {
                    _rockModel.postValue(result[i])
                }

            }

        }
    }


}