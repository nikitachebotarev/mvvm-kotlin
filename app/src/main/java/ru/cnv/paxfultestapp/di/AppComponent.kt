package ru.cnv.paxfultestapp.di

import dagger.Component
import okhttp3.OkHttpClient
import ru.cnv.paxfultestapp.repository.JokesRepository
import ru.cnv.paxfultestapp.repository.SettingsRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, DbModule::class])
interface AppComponent {

    fun getOkHttpClient(): OkHttpClient

    fun getJokesRepository(): JokesRepository

    fun getSettingsRepository(): SettingsRepository
}