package ru.cnv.paxfultestapp.repository.source.http.response

import com.google.gson.annotations.SerializedName

data class JokesResponse(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("value") val jokes: List<Joke>?
) {

    data class Joke(@SerializedName("id") val id: Int?, @SerializedName("joke") val joke: String?)
}