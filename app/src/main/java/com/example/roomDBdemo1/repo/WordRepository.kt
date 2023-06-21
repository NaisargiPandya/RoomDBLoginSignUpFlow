package com.example.roomDBdemo1.repo

import android.content.Context
import androidx.room.Room
import com.example.roomDBdemo1.Database.SignUpFlowDatabase

object WordRepository {
    private var INSTANCE: SignUpFlowDatabase? = null
    fun getInstance(context: Context): SignUpFlowDatabase {
        if (INSTANCE == null) {
            synchronized(SignUpFlowDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) = Room.databaseBuilder(
        context.applicationContext, SignUpFlowDatabase::class.java, "Signup_database"
    ).build()
}
