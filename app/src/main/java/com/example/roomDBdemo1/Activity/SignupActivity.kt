package com.example.roomDBdemo1.Activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.roomDBdemo1.Dao.SignUpDao
import com.example.roomDBdemo1.Database.SignUpFlowDatabase
import com.example.roomDBdemo1.Entity.SignupEntity
import com.example.roomDBdemo1.R
import com.example.roomDBdemo1.databinding.ActivitySignupBinding
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.math.sign


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    lateinit var signUpDao: SignUpDao

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onclick()

        val signUpDB = Room.databaseBuilder(this, SignUpFlowDatabase::class.java, "SIGNUP_DATA")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        signUpDao = signUpDB.doa()
    }

    private fun onclick() {
        binding.btnSignup.setOnClickListener {
            CheckAllFields()
            var studentEntity = 0L
            val rg = findViewById<View>(com.example.roomDBdemo1.R.id.radioGroup) as RadioGroup
            val value = (findViewById<View>(rg.checkedRadioButtonId) as RadioButton)
                .text.toString()
            val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
            val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)

            lifecycleScope.launch {
                studentEntity = signUpDao.signUpInsertNote(
                    SignupEntity(
                        0L,
                        binding.edtFirstName.text.toString(),
                        binding.edtLastName.text.toString(),
                        binding.edtEmail.text.toString(),
                        binding.edtPhone.text.toString(),
                        binding.edtPwd.text.toString(),
                        value,
                        selectedCheckboxes()
                    )
                )
                Log.d("MYTAG", "data is here===> ${studentEntity} ")
                callStudentDetailActivity(studentEntity)
            }
        }
    }

    private fun checkBoxSelectedData() {
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
        return false
    }

    private fun callStudentDetailActivity(studentEntity: Long) {
        if (studentEntity > 0L) {
            startActivity(
                Intent(this, LoginActivity::class.java).putExtra("id", studentEntity)
            )
        }
    }

    private fun CheckAllFields(): Boolean {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )

        fun isValidString(str: String): Boolean {
            return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
        }
        if (binding.edtFirstName.length() == 0) {
            binding.edtFirstName.error = "First name is required"
            return false
        }
        if (binding.edtLastName.length() == 0) {
            binding.edtLastName.error = "Last name is required"
            return false
        }
        if (binding.edtEmail.length() == 0) {
            binding.edtEmail.error = "Email is required"
            return false
        }
        if (binding.edtLastName.length() == 0) {
            binding.edtLastName.error = "Last name is required"
            return false
        }
        if (binding.edtEmail.length() == 0) {
            binding.edtEmail.error = "Email is required"
            return false
        }
        if (!isValidString(binding.edtEmail.text.toString().trim())) {
            binding.edtEmail.error = "Invalid email address"
            return false
        }
        if (binding.edtPhone.length() == 0) {
            binding.edtPhone.error = "Phone number is required"
            return false
        }
        if (binding.edtPhone.length() <= 9) {
            binding.edtPhone.error = "Phone number is less than 10 digit"
            return false
        }
        if (binding.edtPwd.length() == 0) {
            binding.edtPwd.error = "Password is required"
            return false
        }
        if (binding.edtPwd.length() < 8) {
            binding.edtPwd.error = "Password must be minimum 8 characters"
            return false
        }
        if (binding.checkbox1.isChecked || binding.checkbox2.isChecked || binding.checkbox3.isChecked == false) {
            Toast.makeText(
                applicationContext, "Hobby:" +
                        " Please select atList one checkbox",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

}