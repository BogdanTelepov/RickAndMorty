package ru.btelepov.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.btelepov.rickandmorty.Constants.Companion.PAGE_SIZE
import ru.btelepov.rickandmorty.Constants.Companion.PREFETCH_DISTANCE
import ru.btelepov.rickandmorty.models.responses.GetCharacterByIdResponse

class CharactersViewModel : ViewModel() {

    private val repository = CharacterRepository()
    private val dataSourceFactory = CharacterDataSourceFactory(viewModelScope, repository)

    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    val getCharacterByIdResponsePagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()

}