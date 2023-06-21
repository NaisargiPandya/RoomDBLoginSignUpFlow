package com.example.roomDBdemo1.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomDBdemo1.Dao.SignUpDao
import com.example.roomDBdemo1.Entity.SignupEntity

@Database(entities = [SignupEntity::class], version = 1)
abstract class SignUpFlowDatabase : RoomDatabase() {

    abstract fun doa(): SignUpDao

}
