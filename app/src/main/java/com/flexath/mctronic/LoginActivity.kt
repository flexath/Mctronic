package com.flexath.mctronic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.saadahmedsoft.shortintent.Anim
import com.saadahmedsoft.shortintent.ShortIntent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var password: String
    private var studentDatabase:StudentDatabase? = null
    private lateinit var reference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userNameSignIn = findViewById<TextInputLayout>(R.id.userNameInputLayout)
        val passWordSignIn = findViewById<TextInputLayout>(R.id.passwordInputLayout)

        signUpButton.setOnClickListener {
            ShortIntent.getInstance(this).addDestination(SignUpActivity::class.java).also {
                it.addTransition(Anim.SPLIT)
            }
        }

        signInButton.setOnClickListener {

            reference = FirebaseDatabase.getInstance().getReference("students")

            this.userName = userNameSignIn.editText?.text.toString()
            this.password = passWordSignIn.editText?.text.toString()

            if (this.userName.isNotEmpty() && this.password.isNotEmpty()) {
                readFromDatabase(this.userName,this.password)
                userNameSignIn.error = null
                passWordSignIn.error = null
                userNameSignIn.isErrorEnabled = false
                passWordSignIn.isErrorEnabled = false
            }
            else{
                userNameSignIn.error = "Username invalid"
                passWordSignIn.error = "Password invalid"
                Toast.makeText(this, "Login unmatched", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readFromDatabase(username:String,pw:String) {
        reference.child(this.userName).get().addOnSuccessListener {
            if (it.exists()) {
                val fullNameOfUser = it.child("fullName").value.toString()
                val userNameOfUser = it.child("userName").value.toString()
                val currentYearOfUser = it.child("currentYear").value.toString()
                val rollNumberOfUser = it.child("rollNumber").value.toString()
                val emailOfUser = it.child("email").value.toString()
                val mobileNumberOfUser = it.child("mobileNumber").value.toString()
                val passwordOfUser = it.child("password").value.toString()

                studentDatabase = StudentDatabase(fullNameOfUser, userNameOfUser, currentYearOfUser, rollNumberOfUser, emailOfUser, mobileNumberOfUser, passwordOfUser)

                val tag = "LoginActivity"
                Log.d(tag,studentDatabase?.fullName.toString())
                Log.d(tag,studentDatabase?.userName.toString())
                Log.d(tag,studentDatabase?.currentYear.toString())
                Log.d(tag,studentDatabase?.rollNumber.toString())
                Log.d(tag,studentDatabase?.email.toString())
                Log.d(tag,studentDatabase?.mobileNumber.toString())
                Log.d(tag,studentDatabase?.password.toString())

                if (username == studentDatabase?.userName && pw == studentDatabase?.password) {
                    Intent(this@LoginActivity, HomeScreenActivity::class.java).also { intent:Intent ->
                        intent.putExtra("FULL_NAME", studentDatabase?.fullName)
                        intent.putExtra("USER_NAME", studentDatabase?.userName)
                        intent.putExtra("CURRENT_YEAR", studentDatabase?.currentYear)
                        intent.putExtra("ROLL_NUMBER", studentDatabase?.rollNumber)
                        intent.putExtra("EMAIL", studentDatabase?.email)
                        intent.putExtra("MOBILE_NUMBER", studentDatabase?.mobileNumber)
                        intent.putExtra("PASSWORD", studentDatabase?.password)
                        startActivity(intent)
                    }
                }
                else{
                    Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Listening value is not existed", Toast.LENGTH_SHORT)
                    .show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}