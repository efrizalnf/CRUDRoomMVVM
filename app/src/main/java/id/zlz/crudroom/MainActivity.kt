package id.zlz.crudroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.zlz.crudroom.adapter.NoteAdapter
import id.zlz.crudroom.databinding.ActivityEditBinding
import id.zlz.crudroom.databinding.ActivityMainBinding
import id.zlz.crudroom.room.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
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
        noteAdapter = NoteAdapter(arrayListOf())
        binding.listNote.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = noteAdapter
        }
    }

    private fun addNotes() {
        binding.buttonCreate.setOnClickListener {
            val i = Intent(this, EditActivity::class.java)
            startActivity(i)
        }
    }
}