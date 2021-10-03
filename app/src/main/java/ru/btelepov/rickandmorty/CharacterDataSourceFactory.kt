package ru.btelepov.rickandmorty

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import ru.btelepov.rickandmorty.models.responses.GetCharacterByIdResponse
import ru.btelepov.rickandmorty.network.CharacterDataSource

class CharacterDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
) : DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharacterDataSource(coroutineScope, repository)
    }
}