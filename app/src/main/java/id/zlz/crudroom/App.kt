package id.zlz.crudroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.zlz.crudroom.activity.EditActivity
import id.zlz.crudroom.adapter.ItemClickListener
import id.zlz.crudroom.adapter.NoteAdapter
import id.zlz.crudroom.databinding.ActivityMainBinding
import id.zlz.crudroom.room.Const
import id.zlz.crudroom.room.NoteDb
import id.zlz.crudroom.room.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : AppCompatActivity(), ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteAdapter: NoteAdapter
    val db by lazy { NoteDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addNotes()
        setRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            withContext(Dispatchers.Main) {
                noteAdapter.setNoteList(notes)
            }
        }
    }

    private fun setRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf(), this)
        binding.listNote.apply {
            layoutManager =
                LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    private fun addNotes() {
        binding.buttonCreate.setOnClickListener {
            intentOption(0, Const.TYPE_CREATE.ordinal)
        }
    }

    override fun onClick(notes: NoteEntity) {
        intentOption(notes.id, Const.TYPE_READ.ordinal)
    }

    private fun intentOption(noteId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra(LIST_NOTE_ID, noteId)
                .putExtra(LIST_INTENT_TYPE, intentType )
        )
    }

    companion object {
        const val LIST_NOTE_ID = "list_noteid"
        const val LIST_INTENT_TYPE = "list_intenttype"
    }
}