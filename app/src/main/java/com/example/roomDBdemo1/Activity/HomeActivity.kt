package com.example.roomDBdemo1.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roomDBdemo1.Adapter.NoteAdapter
import com.example.roomDBdemo1.Database.NoteDatabase
import com.example.roomDBdemo1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    val NOTE_DATABASE = "note_database"

    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    private fun checkItem() {
        binding.apply {
            if (noteDB.doa().getAllNotes().isNotEmpty()) {
                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
                noteAdapter.differ.submitList(noteDB.doa().getAllNotes())
                setupRecyclerView()
            } else {
                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = noteAdapter
        }

    }
}