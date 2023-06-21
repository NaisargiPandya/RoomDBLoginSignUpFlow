package com.example.roomDBdemo1.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomDBdemo1.NoteDao
import com.example.roomDBdemo1.Entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun doa(): NoteDao
}

