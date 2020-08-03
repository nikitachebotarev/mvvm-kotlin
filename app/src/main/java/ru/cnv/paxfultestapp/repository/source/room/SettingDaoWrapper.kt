package ru.cnv.paxfultestapp.repository.source.room

import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.entity.Settings
import ru.cnv.paxfultestapp.repository.source.room.dao.SettingDao
import ru.cnv.paxfultestapp.repository.source.room.entity.Setting

class SettingDaoWrapper(private val settingDao: SettingDao) {

    fun isOffline(): Observable<Boolean> {
        return settingDao.getAllSettings()
            .map {
                val setting = it.firstOrNull { it.name == Setting.Name.IS_OFFLINE.name }
                if (setting != null) {
                    return@map setting.value == Setting.IsOffline.TRUE.name
                } else {
                    return@map false
                }
            }
    }

    fun getFirstName(): Observable<String> {
        return settingDao.getAllSettings()
            .map {
                val setting = it.firstOrNull { it.name == Setting.Name.FIRST_NAME.name }
                if (setting != null) {
                    return@map setting.value
                } else {
                    return@map "Chuck"
                }
            }
    }

    fun getLastName(): Observable<String> {
        return settingDao.getAllSettings()
            .map {
                val setting = it.firstOrNull { it.name == Setting.Name.LAST_NAME.name }
                if (setting != null) {
                    return@map setting.value
                } else {
                    return@map "Norris"
                }
            }
    }

    fun saveSettings(settings: Settings) {
        settingDao.saveSettings(
            listOf(
                Setting(
                    Setting.Name.FIRST_NAME.name,
                    settings.firstName
                ),
                Setting(
                    Setting.Name.LAST_NAME.name,
                    settings.lastName
                ),
                Setting(
                    Setting.Name.IS_OFFLINE.name,
                    if (settings.isOffline) Setting.IsOffline.TRUE.name else Setting.IsOffline.FALSE.name
                )
            )
        )
    }
}