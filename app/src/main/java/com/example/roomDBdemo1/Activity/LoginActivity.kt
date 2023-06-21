package com.example.roomDBdemo1.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.roomDBdemo1.Dao.SignUpDao
import com.example.roomDBdemo1.Database.SignUpFlowDatabase
import com.example.roomDBdemo1.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var signUpDao: SignUpDao
    var id = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()

        val signUpDB =
            Room.databaseBuilder(this, SignUpFlowDatabase::class.java, "SIGNUP_DATA")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        signUpDao = signUpDB.doa()
    }

    private fun onClick() {
        id = intent.getLongExtra("id", 0L)
        binding.btnLogin.setOnClickListener {
            val userEmail = binding.edtUserName.text.toString().trim()
            val userPwd = binding.edtUserPwd.text.toString().trim()
//            lifecycleScope.launch {
            val loginDataId = signUpDao.logIn(userEmail, userPwd)
            callStudentActivity(loginDataId.id)
//            }
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun callStudentActivity(loginDataId: Long) {
        if (loginDataId > 0) {
            Toast.makeText(this, "Data matched!", Toast.LENGTH_LONG).show()
            startActivity(
                Intent(this, StudentDetailActivity::class.java)
                    .putExtra("id", loginDataId)
            )
        } else {
            Toast.makeText(this, "Data not matched!, Please Register", Toast.LENGTH_LONG)
                .show()
        }
    }
}