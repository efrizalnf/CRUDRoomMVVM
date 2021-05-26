package id.zlz.crudroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.zlz.crudroom.App.Companion.LIST_INTENT_TYPE
import id.zlz.crudroom.App.Companion.LIST_NOTE_ID
import id.zlz.crudroom.databinding.ActivityEditBinding
import id.zlz.crudroom.room.Const
import id.zlz.crudroom.room.NoteDb
import id.zlz.crudroom.room.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    val db by lazy { NoteDb(this) }
    private var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClick()
        setupViewDetail()


    }

    private fun setupViewDetail() {
        val itype = intent.getIntExtra(LIST_INTENT_TYPE, 0)
        when (itype) {
            Const.TYPE_READ.ordinal -> {
                binding.buttonSave.visibility = View.GONE
                setUpdateData()
            }
            Const.TYPE_CREATE.ordinal -> {
                binding.buttonUpdate.visibility = View.GONE
            }
        }
    }

    private fun setupClick() {
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
    }

    private fun setUpdateData() {
        val noteId = intent.getIntExtra(LIST_NOTE_ID, 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNoteItem(noteId)[0]
            binding.editTitle.setText(notes.title)
            binding.editNote.setText(notes.desc)
        }
    }
}