package com.example.roomDBdemo1.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SIGNUP_DATA")
class SignupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "signup_FName")
    val signupFName: String,
    @ColumnInfo(name = "signup_LName")
    val signupLName: String,
    @ColumnInfo(name = "signup_Email")
    var signupEmail: String,
    @ColumnInfo(name = "signup_Phone")
    var signupPhone: String,
    @ColumnInfo(name = "signup_password")
    val signUpPwd: String,
    @ColumnInfo(name = "signup_gender_detail")
    val signer: String,
    @ColumnInfo(name = "signup_checkbox")
    val signupCheckbox: Boolean,
//    @ColumnInfo(name = "signup_hobby")
//    val signupHobby: String,
//    @ColumnInfo(name = "signup_hobby")
//    val signupHobby: String,
)