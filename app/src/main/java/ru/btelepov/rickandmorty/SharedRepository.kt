package ru.btelepov.rickandmorty


import ru.btelepov.rickandmorty.domain.Character
import ru.btelepov.rickandmorty.models.responses.CharacterMapper
import ru.btelepov.rickandmorty.models.responses.GetCharacterByIdResponse
import ru.btelepov.rickandmorty.models.responses.GetEpisodeByIdResponse
import ru.btelepov.rickandmorty.network.NetworkModule

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkModule.apiClient.getCharacterById(characterId)
        if (request.failed || !request.isSuccessFul) {
            return null
        }
        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        return CharacterMapper.buildFrom(request.body, networkEpisodes)
    }


    private suspend fun getEpisodesFromCharacterResponse(characterResponse: GetCharacterByIdResponse): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkModule.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessFul) {
            return emptyList()
        }

        return request.body

    }
}