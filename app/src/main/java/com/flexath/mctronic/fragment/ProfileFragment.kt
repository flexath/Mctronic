package com.flexath.mctronic.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.flexath.mctronic.R
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fullName = activity?.intent?.extras?.getString("FULL_NAME").toString()
        val userName = activity?.intent?.extras?.getString("USER_NAME").toString()
        val currentYear = activity?.intent?.extras?.getString("CURRENT_YEAR").toString()
        val rollNumber = activity?.intent?.extras?.getString("ROLL_NUMBER").toString()
        val email = activity?.intent?.extras?.getString("EMAIL").toString()
        val mobileNumber = activity?.intent?.extras?.getString("MOBILE_NUMBER").toString()
        val password = activity?.intent?.extras?.getString("PASSWORD").toString()

        txtUserName.text = userName
        txtFullName.text = fullName
        txtCurrentYear.text = currentYear
        txtRollNumber.text = rollNumber
        txtEmail.text = email
        txtMobileNumber.text = mobileNumber
        txtPassword.text = password

        when(rollNumber.toInt()) {
            14 -> imgStudent.setImageResource(R.drawable.ath)
            9 -> imgStudent.setImageResource(R.drawable.abe)
            10 -> imgStudent.setImageResource(R.drawable.zbk)
            6 -> imgStudent.setImageResource(R.drawable.kll)
        }
    }
}