package id.zlz.crudroom.room

import androidx.room.*


@Dao
interface NoteDao {

    @Insert
    fun insertdata(data: NoteEntity)

    @Update
    fun updatedata(data: NoteEntity)

    @Delete
    fun deletedata(data: NoteEntity)

    @Query("SELECT * from note")
    fun getNotes(): List<NoteEntity>

//    @Query("SELECT * FROM NoteEntity WHERE id =:noteid")
//    suspend fun getNoteData(noteid: Int): List<NoteEntity>


}