package com.example.roomDBdemo1.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomDBdemo1.Entity.SignupEntity

@Dao
interface SignUpDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun signUpInsertNote(signupEntity: SignupEntity) : Long

    @Update
    fun signUpUpdateNote(signupEntity: SignupEntity)

    @Delete
    fun signUpDeleteNote(signupEntity: SignupEntity)

    @Query("SELECT * FROM SIGNUP_DATA")
    fun getAllNotes(): MutableList<SignupEntity>

    @Query("SELECT * FROM SIGNUP_DATA WHERE id LIKE :id")
    fun getStudent(id: Long): SignupEntity

    @Query("Select * FROM SIGNUP_DATA Where signup_Email = :signup_Email AND signup_password = :signup_password")
     fun logIn(signup_Email : String, signup_password : String) : SignupEntity

    @Query("Select EXISTS (Select * FROM SIGNUP_DATA Where signup_FName = :signup_FName AND signup_LName =:signup_LName AND signup_Email = :signup_Email AND signup_Phone =:signup_Phone AND  signup_password = :signup_password)")
    fun getData(signup_FName : String, signup_LName : String, signup_Email : String, signup_Phone : String, signup_password: String) : Boolean
}