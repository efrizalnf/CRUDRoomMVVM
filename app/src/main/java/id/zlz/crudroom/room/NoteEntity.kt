package id.zlz.crudroom.room

import androidx.room.Entity


@Entity
data class NoteEntity (
    val id : Int,
    val title: String,
    val desc: String
)