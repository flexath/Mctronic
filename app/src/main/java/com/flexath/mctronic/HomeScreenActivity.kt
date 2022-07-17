package com.flexath.mctronic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.flexath.mctronic.fragment.ActivityFragment
import com.flexath.mctronic.fragment.HomeFragment
import com.flexath.mctronic.fragment.NotificationFragment
import com.flexath.mctronic.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        bottomNavigation.getOrCreateBadge(R.id.notiBottom).apply {
            number = 5
            isVisible = true
        }

        val homeFragment = HomeFragment()
        val notificationFragment = NotificationFragment()
        val activityFragment = ActivityFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeBottom -> setCurrentFragment(homeFragment)
                R.id.notiBottom -> setCurrentFragment(notificationFragment)
                R.id.activityBottom -> setCurrentFragment(activityFragment)
                R.id.profileBottom -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profileFragmentLayout,fragment)
            commit()
        }
    }
}