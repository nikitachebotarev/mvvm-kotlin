package ru.cnv.paxfultestapp.repository.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.source.room.entity.Setting

@Dao
interface SettingDao {

    @Query("select * from setting")
    fun getAllSettings(): Observable<List<Setting>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSettings(settings: List<Setting>)
}