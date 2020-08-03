package ru.cnv.paxfultestapp.repository

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import ru.cnv.paxfultestapp.repository.entity.Joke
import ru.cnv.paxfultestapp.repository.entity.Settings
import ru.cnv.paxfultestapp.repository.source.http.ApiServiceWrapper
import ru.cnv.paxfultestapp.repository.source.room.JokeDaoWrapper

class JokesRepositoryImpl(
    val settingsRepository: SettingsRepository,
    val jokeDaoWrapper: JokeDaoWrapper,
    val apiServiceWrapper: ApiServiceWrapper
) : JokesRepository {

    override fun getRandomJokes(): Observable<List<Joke>> {
        return Observable.defer {
            val settings = settingsRepository.getSettings().blockingFirst()
            if (settings.isOffline.not()) {
                val remoteJokes = apiServiceWrapper.get10Jokes()
                remoteJokes.forEach { jokeDaoWrapper.saveJoke(it) }
            }

            val randomIds = jokeDaoWrapper.getJokes(10).blockingFirst()
                .fold(mutableListOf<Int>(), { list, joke ->
                    list.add(joke.id)
                    return@fold list
                })

            return@defer jokeDaoWrapper.getJokesByIds(randomIds)
                .map { jokes ->
                    return@map jokes.map {
                        val text = it.text.replace("Chuck", settings.firstName)
                            .replace("Norris", settings.lastName)
                        it.copy(text = text)
                    }
                }
        }
    }

    override fun getMyJokes(): Observable<List<Joke>> {
        return jokeDaoWrapper.getMyJokes()
    }

    override fun likeJoke(id: Int, isLike: Boolean) {
        jokeDaoWrapper.likeJoke(id, isLike)
    }

    override fun deleteJoke(id: Int) {
        jokeDaoWrapper.dislikeJoke(id)
    }

    override fun addJoke(text: String) {
        jokeDaoWrapper.saveJoke(Joke(0, text, true))
    }
}