package ru.cnv.paxfultestapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.cnv.paxfultestapp.app.App
import ru.cnv.paxfultestapp.repository.JokesRepository
import ru.cnv.paxfultestapp.repository.entity.Joke

class MyJokesViewModel : BaseViewModel() {

    private val jokesRepository: JokesRepository = App.component.getJokesRepository()

    val jokes: MutableLiveData<List<Joke>> = MutableLiveData()

    init {
        jokesRepository.getMyJokes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokes.value = it }, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }

    fun deleteJoke(id: Int) {
        Completable.create {
            jokesRepository.deleteJoke(id)
        }.subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }

    fun saveJoke(text: String) {
        Completable.create {
            jokesRepository.addJoke(text)
        }.subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }
}