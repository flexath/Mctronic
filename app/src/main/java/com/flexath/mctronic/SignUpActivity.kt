package com.flexath.mctronic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseInstance: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fullNameEt = findViewById<TextInputLayout>(R.id.etFullName)
        val userNameEt = findViewById<TextInputLayout>(R.id.etUsernameSignUp)
        val currentYearEt = findViewById<TextInputLayout>(R.id.etCurrentYear)
        val rollNumberEt = findViewById<TextInputLayout>(R.id.etRollNumber)
        val emailEt = findViewById<TextInputLayout>(R.id.etEmail)
        val mobileNumberEt = findViewById<TextInputLayout>(R.id.etMobileNumber)
        val passwordEt = findViewById<TextInputLayout>(R.id.etSignUpPassword)


        btnRegister.setOnClickListener {
            firebaseInstance = FirebaseDatabase.getInstance()
            reference = firebaseInstance.getReference("students")

            val fullName = fullNameEt.editText?.text.toString()
            val userName = userNameEt.editText?.text.toString()
            val currentYear = currentYearEt.editText?.text.toString()
            val rollNumber = rollNumberEt.editText?.text.toString()
            val email = emailEt.editText?.text.toString()
            val mobileNumber = mobileNumberEt.editText?.text.toString()
            val password = passwordEt.editText?.text.toString()

            val studentDatabase = StudentDatabase(fullName,userName,currentYear,rollNumber,email,mobileNumber,password)
            reference.child(userName).setValue(studentDatabase)
            finish()
        }

    }
}