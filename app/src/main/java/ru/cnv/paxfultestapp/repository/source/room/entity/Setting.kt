package ru.cnv.paxfultestapp.repository.source.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Setting(@PrimaryKey val name: String, val value: String) {

    enum class Name {
        IS_OFFLINE, FIRST_NAME, LAST_NAME
    }

    enum class IsOffline {
        TRUE, FALSE
    }
}