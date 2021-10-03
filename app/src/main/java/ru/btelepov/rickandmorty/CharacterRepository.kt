package ru.btelepov.rickandmorty


import ru.btelepov.rickandmorty.models.responses.GetCharacterPageResponse
import ru.btelepov.rickandmorty.network.NetworkModule

class CharacterRepository {

    suspend fun getCharactersPage(pageIndex: Int): GetCharacterPageResponse? {
        val request = NetworkModule.apiClient.getCharactersPage(pageIndex)
        if (request.failed || !request.isSuccessFul) {
            return null
        }

        return request.body
    }
}