package ru.cnv.paxfultestapp.repository

import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.entity.Joke


interface JokesRepository {
    fun getRandomJokes(): Observable<List<Joke>>
    fun getMyJokes(): Observable<List<Joke>>
    fun likeJoke(id: Int, isLike: Boolean)
    fun deleteJoke(id: Int)
    fun addJoke(text: String)
}