package ru.cnv.paxfultestapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.cnv.paxfultestapp.app.App
import ru.cnv.paxfultestapp.repository.entity.Settings

class SettingsViewModel : BaseViewModel() {

    private val repository = App.component.getSettingsRepository()

    val settings: MutableLiveData<Settings?> = MutableLiveData(null)

    init {
        repository.getSettings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ settings.value = it }, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }

    fun save(settings: Settings) {
        Completable.create {
            repository.saveSettings(settings)
        }.subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }
}