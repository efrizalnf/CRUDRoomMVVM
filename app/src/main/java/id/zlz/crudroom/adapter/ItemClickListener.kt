package id.zlz.crudroom.adapter

import android.os.Parcel
import android.os.Parcelable
import id.zlz.crudroom.room.NoteEntity

interface ItemClickListener {

    fun onClick(notes: NoteEntity)


}