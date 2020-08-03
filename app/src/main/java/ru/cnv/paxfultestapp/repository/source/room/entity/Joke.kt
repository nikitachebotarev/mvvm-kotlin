package ru.cnv.paxfultestapp.repository.source.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val isFavorite: Boolean
)