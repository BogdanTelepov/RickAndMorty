package ru.btelepov.rickandmorty

class Constants {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"

        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = PAGE_SIZE * 2

        const val ARG_CHARACTER_ID = "character_id"
    }
}