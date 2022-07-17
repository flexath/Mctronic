package com.flexath.mctronic

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saadahmedsoft.shortintent.Anim
import com.saadahmedsoft.shortintent.ShortIntent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUpButton.setOnClickListener {
            ShortIntent.getInstance(this).addDestination(SignUpActivity::class.java).also {
                it.addTransition(Anim.SPLIT)
            }
        }

        signInButton.setOnClickListener {
            ShortIntent.getInstance(this).addDestination(HomeScreenActivity::class.java).also {
                it.addTransition(Anim.SLIDERIGHT)
            }
        }
    }
}