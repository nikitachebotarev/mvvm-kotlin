package ru.cnv.paxfultestapp.repository.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import ru.cnv.paxfultestapp.repository.source.room.entity.Joke

@Dao
interface JokeDao {

    @Query("select * from joke where id in (:ids)")
    fun getJokesByIds(ids: List<Int>): Observable<List<Joke>>

    @Query("select * from joke order by random() limit :limit")
    fun getJokes(limit: Int): Observable<List<Joke>>

    @Query("select * from joke where isFavorite = 1")
    fun getMyJokes(): Observable<List<Joke>>

    @Insert
    fun saveJoke(joke: Joke)

    @Query("update joke set isFavorite = :like where id = :id")
    fun likeJoke(like: Boolean, id: Int)
}