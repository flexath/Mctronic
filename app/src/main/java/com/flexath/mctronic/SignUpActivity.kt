package com.flexath.mctronic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseInstance: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var fullNameEt:TextInputLayout
    private lateinit var userNameEt:TextInputLayout
    private lateinit var currentYearEt:TextInputLayout
    private lateinit var rollNumberEt:TextInputLayout
    private lateinit var emailEt:TextInputLayout
    private lateinit var mobileNumberEt:TextInputLayout
    private lateinit var passwordEt:TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fullNameEt = findViewById(R.id.etFullName)
        userNameEt = findViewById(R.id.etUsernameSignUp)
        currentYearEt = findViewById(R.id.etCurrentYear)
        rollNumberEt = findViewById(R.id.etRollNumber)
        emailEt = findViewById(R.id.etEmail)
        mobileNumberEt = findViewById(R.id.etMobileNumber)
        passwordEt = findViewById(R.id.etSignUpPassword)


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

            if(isValidFullName(fullName) && isValidUsername(userName) && isValidYear(currentYear) && isValidRollNumber() && isValidEmail(email) && isValidMobileNumber(mobileNumber) && isValidPassword(password)){
                val studentDatabase = StudentDatabase(fullName,userName,currentYear,rollNumber,email,mobileNumber,password)
                reference.child(userName).setValue(studentDatabase)
                finish()
            }else{
                return@setOnClickListener
            }
        }
    }

    private fun isValidFullName(fullName:String) :Boolean {
        if(fullName.isEmpty()) {
            fullNameEt.error = "Invalid! Full Name field is empty"
            return false
        }else{
            fullNameEt.error = null
            fullNameEt.isErrorEnabled = false
            return true
        }
    }

    private fun isValidUsername(userName:String):Boolean {
        for(i in userName) {
            if(i == ' ') {
                userNameEt.error = "Invalid! Username cannot contain space between words"
                return false
            }
        }
        if(userName.isEmpty()) {
            userNameEt.error = "Invalid! Username field is empty"
            return false
        }
        else if(userName.trimmedLength() > 10) {
            userNameEt.error = "Invalid! Username shouldn't > 10 characters"
            return false
        }
        else{
            userNameEt.error = null
            userNameEt.isErrorEnabled = false
            return true
        }
    }

    private fun isValidYear(currentYear:String) :Boolean {

        if(currentYear.isEmpty()) {
            currentYearEt.error = "Invalid! Current Year field is empty"
            return false
        }

        for(i in currentYear) {
            if(currentYear.length > 1 || !((i.code >= 49) && (i.code <= 54)) ) {
                currentYearEt.error = "Invalid! Choose between 1 and 6"
                return false
            }
            else{
                currentYearEt.error = null
                currentYearEt.isErrorEnabled = false
                return true
            }
        }
        return false
    }

    private fun isValidRollNumber() : Boolean {
        val roll = rollNumberEt.editText?.text.toString()

        if(roll.isEmpty()){
            rollNumberEt.error = "Invalid! Roll Number field is empty"
            return false
        }
        else{
            for(i in roll) {
                if(i in 'a'..'w' || i in 'A'..'W') {
                    rollNumberEt.error = "Invalid! Choose between 1 and 45"
                    return false
                }
            }
            if(roll.toInt() in (1..45)) {
                rollNumberEt.error = null
                rollNumberEt.isErrorEnabled = false
                return true
            }else{
                rollNumberEt.error = "Invalid! Choose between 1 and 45"
                rollNumberEt.isErrorEnabled = true
                return false
            }
        }
    }

    private fun isValidEmail(email:String) :Boolean {
        val postString = "@gmail.com"
        if(email.isEmpty()) {
            emailEt.error = "Invalid! Email Address field is empty"
            return false
        }
        else{
            val replacedString = email.replace(postString,"")

            Log.i("SignUpActivity",replacedString)
            if(email == replacedString+postString) {
                emailEt.error = null
                emailEt.isErrorEnabled = false
                return true
            }
            else{
                emailEt.error = "Invalid! Email Address must be name+$postString"
                return false
            }
        }
    }

    private fun isValidMobileNumber(mobileNumber:String) :Boolean {

        if(mobileNumber.isEmpty()) {
            mobileNumberEt.error = "Invalid! Mobile Number field is empty"
            return false
        }
        else{
            if(mobileNumber.length > 11) {
                mobileNumberEt.error = "Invalid! Mobile Number shouldn't > 11 characters"
                return false
            }
            else{
                if(mobileNumber.length == 11 && mobileNumber.startsWith("09")){
                    mobileNumberEt.error = null
                    mobileNumberEt.isErrorEnabled = false
                    return true
                }
                else if(!(mobileNumber.startsWith("09"))){
                    mobileNumberEt.error = "Invalid! Mobile Number should start with 09"
                    return false
                }
                else{
                    mobileNumberEt.error = "Invalid! No of password must be 11 characters"
                    return false
                }
            }
        }
    }

    private fun isValidPassword(password:String) :Boolean {
        if(password.isEmpty()) {
            passwordEt.error = "Invalid! Password field is empty"
            return false
        }else{
            mobileNumberEt.error = null
            mobileNumberEt.isErrorEnabled = false
            return true
        }
    }
}