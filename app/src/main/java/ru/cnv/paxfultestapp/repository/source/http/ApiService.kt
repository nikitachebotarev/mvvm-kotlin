package ru.cnv.paxfultestapp.repository.source.http

import retrofit2.Call
import retrofit2.http.GET
import ru.cnv.paxfultestapp.repository.source.http.response.JokesResponse

interface ApiService {

    companion object {

        const val BASE_URL = "https://api.icndb.com/"
    }

    @GET("jokes/random/10")
    fun get10Jokes(): Call<JokesResponse>
}