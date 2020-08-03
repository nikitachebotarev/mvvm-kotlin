package ru.cnv.paxfultestapp.repository.source.http

import ru.cnv.paxfultestapp.repository.entity.Joke
import ru.cnv.paxfultestapp.repository.source.http.response.JokesResponse

class ApiServiceWrapper(val apiService: ApiService) {

    fun get10Jokes(): List<Joke> {
        val result = runCatching { apiService.get10Jokes().execute().body()!! }
        result.exceptionOrNull()?.printStackTrace()
        val response = result.getOrElse { JokesResponse(false, emptyList()) }
        return response.jokes!!
            .filter { it.id != null && it.joke != null }
            .map { Joke(it.id!!, it.joke!!, false) }
    }
}