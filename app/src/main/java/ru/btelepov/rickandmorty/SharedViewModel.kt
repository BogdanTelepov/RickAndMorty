package ru.btelepov.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val sharedRepository = SharedRepository()

    private var _getCharacterByIdResponse: MutableLiveData<ru.btelepov.rickandmorty.domain.Character?> =
        MutableLiveData()
    val getCharacterByIdResponse: LiveData<ru.btelepov.rickandmorty.domain.Character?> get() = _getCharacterByIdResponse


    fun getCharacterById(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = sharedRepository.getCharacterById(characterId)
            _getCharacterByIdResponse.postValue(response)
        }
    }
}