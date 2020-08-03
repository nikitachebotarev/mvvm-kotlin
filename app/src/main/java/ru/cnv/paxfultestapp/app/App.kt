package ru.cnv.paxfultestapp.app

import android.app.Application
import ru.cnv.paxfultestapp.di.*

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .dbModule(DbModule(this))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}