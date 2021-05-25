package id.zlz.crudroom.room

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertdata(data: NoteEntity)

    @Update
    suspend fun updatedata(data: NoteEntity)

    @Delete
    suspend fun deletedata(data:NoteEntity)

    @Query("SELECT * FROM notesdb ORDER BY id DESC")
    suspend fun getNotes() : List<NoteEntity>

    @Query("SELECT * FROM notesdb WHERE id =:noteid")
    suspend fun getNoteData(noteid: Int): List<NoteEntity>

    

}