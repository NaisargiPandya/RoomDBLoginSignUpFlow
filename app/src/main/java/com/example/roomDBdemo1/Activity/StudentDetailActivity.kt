package com.example.roomDBdemo1.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.room.Room
import com.example.roomDBdemo1.Dao.SignUpDao
import com.example.roomDBdemo1.Database.SignUpFlowDatabase
import com.example.roomDBdemo1.Entity.SignupEntity
import com.example.roomDBdemo1.R

import com.example.roomDBdemo1.databinding.ActivityStudentDetailBinding

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailBinding
    lateinit var signUpDao: SignUpDao
    var id = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signUpDB =
            Room.databaseBuilder(this, SignUpFlowDatabase::class.java, "SIGNUP_DATA")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        signUpDao = signUpDB.doa()

        getUserData()
        binding.btnSubmit.setOnClickListener {
            updateUserdata()
        }
    }

    private fun updateUserdata() {
        val rg = findViewById<View>(R.id.radioGroup) as RadioGroup
        val value = (findViewById<View>(rg.checkedRadioButtonId) as RadioButton)
            .text.toString()
        val selectedCheckboxes = mutableSetOf<String>()
        if (binding.checkbox1.isChecked) {
            selectedCheckboxes.add("Option 1")
        }
        if (binding.checkbox2.isChecked) {
            selectedCheckboxes.add("Option 2")
        }
        if (binding.checkbox3.isChecked) {
            selectedCheckboxes.add("Option 2")
        }
        signUpDao.signUpUpdateNote(
            signupEntity = SignupEntity(
                id,
                binding.edtFirstName.text.toString(),
                binding.edtLastName.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtPhone.text.toString(),
                binding.edtPwd.text.toString(),
                value,
                selectedCheckboxes()
            )
        )
    }

    private fun selectedCheckboxes(): Boolean {
        var result = "Selected Courses"
        if (binding.checkbox1.isChecked()) {
            result += "\ncheckbox1"
        }
        if (binding.checkbox2.isChecked()) {
            result += "\ncheckbox2"
        }
        if (binding.checkbox3.isChecked()) {
            result += "\ncheckbox3"
        }
        Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()

        return true
    }

    private fun getUserData() {
        id = intent.getLongExtra("id", 0L)
        val studentdata = signUpDao.getStudent(id)
        val selectedCheckboxes = String()
        if (binding.checkbox1.isChecked) {
            selectedCheckboxes
        }
        if (binding.checkbox2.isChecked) {
            selectedCheckboxes
        }
        if (binding.checkbox3.isChecked) {
            selectedCheckboxes
        }

        Log.d("MYTAG", "DATA is here====> ${studentdata} ")
        binding.edtFirstName.setText(studentdata.signupFName)
        binding.edtLastName.setText(studentdata.signupLName)
        binding.edtEmail.setText(studentdata.signupEmail)
        binding.edtPhone.setText(studentdata.signupPhone)
        binding.edtPwd.setText(studentdata.signUpPwd)
        val value = studentdata.signer
        if (value == "Male") {
            binding.radioGroup.check(binding.radio1.id)
            binding.radio1.isChecked = true
        } else {
            binding.radioGroup.check(binding.radio2.id)
            binding.radio2.isChecked = true
        }
        val checkBoxData = studentdata.signupCheckbox
        selectedCheckboxes()
    }
}