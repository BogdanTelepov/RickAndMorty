package ru.btelepov.rickandmorty.network

import retrofit2.Response
import ru.btelepov.rickandmorty.models.responses.GetCharacterByIdResponse
import ru.btelepov.rickandmorty.models.responses.GetCharacterPageResponse
import ru.btelepov.rickandmorty.models.responses.GetEpisodeByIdResponse

class ApiClient(private val apiService: ApiService) {


    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { apiService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharacterPageResponse> {
        return safeApiCall { apiService.getCharactersPage(pageIndex) }
    }

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { apiService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange:String):SimpleResponse<List<GetEpisodeByIdResponse>>{
        return safeApiCall { apiService.getEpisodeRange(episodeRange) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}