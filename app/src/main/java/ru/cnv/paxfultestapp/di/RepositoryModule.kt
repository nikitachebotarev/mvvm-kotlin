package ru.cnv.paxfultestapp.di

import dagger.Module
import dagger.Provides
import ru.cnv.paxfultestapp.repository.JokesRepository
import ru.cnv.paxfultestapp.repository.JokesRepositoryImpl
import ru.cnv.paxfultestapp.repository.SettingsRepository
import ru.cnv.paxfultestapp.repository.SettingsRepositoryImpl
import ru.cnv.paxfultestapp.repository.source.http.ApiServiceWrapper
import ru.cnv.paxfultestapp.repository.source.room.JokeDaoWrapper
import ru.cnv.paxfultestapp.repository.source.room.SettingDaoWrapper
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun jokesRepository(
        settingsRepository: SettingsRepository,
        jokeDaoWrapper: JokeDaoWrapper,
        apiServiceWrapper: ApiServiceWrapper
    ): JokesRepository {
        return JokesRepositoryImpl(settingsRepository, jokeDaoWrapper, apiServiceWrapper)
    }

    @Provides
    fun settingsRepository(settingsDaoWrapper: SettingDaoWrapper): SettingsRepository {
        return SettingsRepositoryImpl(settingsDaoWrapper)
    }
}