package ru.btelepov.rickandmorty

import android.app.Application
import android.content.Context

class RickAndMortyApplication : Application() {




    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: RickAndMortyApplication
        fun getContext(): Context? {
            return instance.applicationContext
        }
    }
}