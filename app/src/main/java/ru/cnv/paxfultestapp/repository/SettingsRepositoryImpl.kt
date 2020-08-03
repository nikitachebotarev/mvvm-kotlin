package ru.cnv.paxfultestapp.repository

import io.reactivex.Observable
import io.reactivex.functions.Function3
import ru.cnv.paxfultestapp.repository.entity.Settings
import ru.cnv.paxfultestapp.repository.source.room.SettingDaoWrapper

class SettingsRepositoryImpl(private val settingDaoWrapper: SettingDaoWrapper) :
    SettingsRepository {

    override fun getSettings(): Observable<Settings> {
        return Observable.combineLatest(
            settingDaoWrapper.getFirstName(),
            settingDaoWrapper.getLastName(),
            settingDaoWrapper.isOffline(),
            Function3<String, String, Boolean, Settings> { first, last, isOffline ->
                Settings(first, last, isOffline)
            }
        )
    }

    override fun saveSettings(settings: Settings) {
        settingDaoWrapper.saveSettings(settings)
    }
}