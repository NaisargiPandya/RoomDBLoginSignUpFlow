package com.example.roomDBdemo1.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.roomDBdemo1.Database.NoteDatabase
import com.example.roomDBdemo1.Entity.NoteEntity
import com.example.roomDBdemo1.databinding.ActivityAddNoteBinding
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    val NOTE_DATABASE = "note_database"
    private lateinit var noteEntity: NoteEntity

    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(0, title, desc)
                    noteDB.doa().insertNote(noteEntity)
                    finish()
                } else {
                    Snackbar.make(
                        it, "Title and Description cannot be Empty",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show()
                }
            }
        }
    }
}