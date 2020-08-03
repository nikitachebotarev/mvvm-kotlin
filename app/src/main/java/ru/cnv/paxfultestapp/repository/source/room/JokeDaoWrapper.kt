package ru.cnv.paxfultestapp.repository.source.room

import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.entity.Joke
import ru.cnv.paxfultestapp.repository.source.room.dao.JokeDao

class JokeDaoWrapper(private val jokeDao: JokeDao) {

    fun getJokesByIds(ids: List<Int>): Observable<List<Joke>> {
        return jokeDao.getJokesByIds(ids).map { it.map { Joke(it.id, it.text, it.isFavorite) } }
    }

    fun getJokes(limit: Int): Observable<List<Joke>> {
        return jokeDao.getJokes(limit).map { it.map { Joke(it.id, it.text, it.isFavorite) } }
    }

    fun getMyJokes(): Observable<List<Joke>> {
        return jokeDao.getMyJokes().map { it.map { Joke(it.id, it.text, it.isFavorite) } }
    }

    fun likeJoke(id: Int, isLike: Boolean) {
        jokeDao.likeJoke(isLike, id)
    }

    fun dislikeJoke(id: Int) {
        jokeDao.likeJoke(false, id)
    }

    fun saveJoke(joke: Joke) {
        jokeDao.saveJoke(joke.run {
            ru.cnv.paxfultestapp.repository.source.room.entity.Joke(
                text = text,
                isFavorite = isFavorite
            )
        })
    }
}