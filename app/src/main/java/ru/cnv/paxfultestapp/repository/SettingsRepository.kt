package ru.cnv.paxfultestapp.repository

import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.entity.Settings

interface SettingsRepository {
    fun getSettings(): Observable<Settings>
    fun saveSettings(settings: Settings)
}