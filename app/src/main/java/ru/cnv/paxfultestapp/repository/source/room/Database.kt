package ru.cnv.paxfultestapp.repository.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cnv.paxfultestapp.repository.source.room.dao.JokeDao
import ru.cnv.paxfultestapp.repository.source.room.dao.SettingDao
import ru.cnv.paxfultestapp.repository.source.room.entity.Joke
import ru.cnv.paxfultestapp.repository.source.room.entity.Setting

@Database(entities = [Joke::class, Setting::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

    abstract fun settingDao(): SettingDao
}