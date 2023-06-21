package com.example.roomDBdemo1.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.roomDBdemo1.Database.NoteDatabase
import com.example.roomDBdemo1.Entity.NoteEntity
import com.example.roomDBdemo1.databinding.ActivityUpdateNoteBinding
import com.google.android.material.snackbar.Snackbar

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    var NOTE_DATABASE = "note_database"
    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var noteEntity: NoteEntity
    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""
    val BUNDLE_NOTE_ID = "bundle_note_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId = it.getInt(BUNDLE_NOTE_ID)
        }

        binding.apply {
            defaultTitle = noteDB.doa().getNote(noteId).noteTitle
            defaultDesc = noteDB.doa().getNote(noteId).noteDesc

            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteId, defaultTitle, defaultDesc)
                noteDB.doa().deleteNote(noteEntity)
                finish()
            }

            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(noteId, title, desc)
                    noteDB.doa().updateNote(noteEntity)
                    finish()
                } else {
                    Snackbar.make(
                        it, "Title and Description cannot be Empty",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)

                }
            }
        }

    }
}