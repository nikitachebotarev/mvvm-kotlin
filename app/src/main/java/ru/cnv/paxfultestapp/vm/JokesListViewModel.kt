package ru.cnv.paxfultestapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.cnv.paxfultestapp.app.App
import ru.cnv.paxfultestapp.repository.JokesRepository
import ru.cnv.paxfultestapp.repository.entity.Joke

class JokesListViewModel : BaseViewModel() {

    private val jokesRepository: JokesRepository = App.component.getJokesRepository()

    val jokes: MutableLiveData<List<Joke>> = MutableLiveData()

    init {
        loadRandomJokes()
    }

    fun like(id: Int, isLike: Boolean) {
        Completable.create {
            jokesRepository.likeJoke(id, isLike)
        }.subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }

    fun shake() {
        viewModelDisposable.clear()
        loadRandomJokes()
    }

    private fun loadRandomJokes() {
        jokesRepository.getRandomJokes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokes.value = it }, { it.printStackTrace() })
            .addTo(viewModelDisposable)
    }
}