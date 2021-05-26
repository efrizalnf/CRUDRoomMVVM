package id.zlz.crudroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.zlz.crudroom.App.Companion.LIST_INTENT_TYPE
import id.zlz.crudroom.App.Companion.LIST_NOTE_ID
import id.zlz.crudroom.databinding.ActivityEditBinding
import id.zlz.crudroom.databinding.ItemNoteBinding
import id.zlz.crudroom.room.Const
import id.zlz.crudroom.room.NoteDb
import id.zlz.crudroom.room.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var bindingitem: ItemNoteBinding
    val db by lazy { NoteDb(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        bindingitem = ItemNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewDetail()
    }

    private fun setupViewDetail() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val itype = intent.getIntExtra(LIST_INTENT_TYPE, 0)
        when (itype) {
            Const.TYPE_CREATE.ordinal -> {
                binding.buttonUpdate.visibility = View.GONE
                setClickListener()
            }
            Const.TYPE_READ.ordinal -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                setReadData()
            }
            Const.TYPE_UPDATE.ordinal -> {
                binding.buttonSave.visibility = View.GONE
                setReadData()
                setClickListener()
            }
        }
    }

    private fun setClickListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().insertdata(
                    NoteEntity(
                        0,
                        binding.editTitle.text.toString(),
                        binding.editNote.text.toString()
                    )
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            val noteId = intent.getIntExtra(LIST_NOTE_ID, 0)
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updatedata(
                    NoteEntity(
                        noteId,
                        binding.editTitle.text.toString(),
                        binding.editNote.text.toString()
                    )
                )
                finish()
            }
        }
    }

    private fun setReadData() {
        val noteId = intent.getIntExtra(LIST_NOTE_ID, 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNoteItem(noteId)[0]
            binding.editTitle.setText(notes.title)
            binding.editNote.setText(notes.desc)
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}