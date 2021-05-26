package id.zlz.crudroom.adapter

import android.os.Parcel
import android.os.Parcelable
import id.zlz.crudroom.room.NoteEntity

interface ItemClickListener {

    fun onRead(notes: NoteEntity)
    fun onUpdate(notes: NoteEntity)


}