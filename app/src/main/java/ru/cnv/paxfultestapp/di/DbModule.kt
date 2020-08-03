package ru.cnv.paxfultestapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.cnv.paxfultestapp.repository.source.room.*
import ru.cnv.paxfultestapp.repository.source.room.dao.JokeDao
import ru.cnv.paxfultestapp.repository.source.room.dao.SettingDao

@Module
class DbModule(val context: Context) {

    @Provides
    fun database(): Database {
        return Room.databaseBuilder(context, Database::class.java, "default").build()
    }

    @Provides
    fun settingDao(database: Database): SettingDao {
        return database.settingDao()
    }

    @Provides
    fun jokeDao(database: Database): JokeDao {
        return database.jokeDao()
    }

    @Provides
    fun settingDaoWrapper(settingDao: SettingDao): SettingDaoWrapper {
        return SettingDaoWrapper(settingDao)
    }

    @Provides
    fun jokeDaoWrapper(jokeDao: JokeDao): JokeDaoWrapper {
        return JokeDaoWrapper(jokeDao)
    }
}