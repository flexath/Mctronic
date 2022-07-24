package com.flexath.mctronic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.sarnava.textwriter.TextWriter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val logoAnimation: Animation = AnimationUtils.loadAnimation(this,R.anim.logo_anim)
        val typeOfApp: Animation = AnimationUtils.loadAnimation(this,R.anim.studentapp_name_text_anim)

        imageLogo.startAnimation(logoAnimation)
        studentAppName.startAnimation(typeOfApp)

        val typeWriter = findViewById<TextWriter>(R.id.typeWriter)
        typeWriter.setWidth(10F)           //scaleType
        typeWriter.setDelay(30)
        typeWriter.setColor(Color.DKGRAY)
        typeWriter.setConfig(TextWriter.Configuration.INTERMEDIATE)
        typeWriter.setSizeFactor(35f)
        typeWriter.setLetterSpacing(20f)
        typeWriter.setText("MECHATRONICS")
        typeWriter.setListener{
            Intent(this,LoginActivity::class.java).also {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, androidx.core.util.Pair(imageLogo, imageLogo.transitionName),
                    androidx.core.util.Pair(typeWriter, typeWriter.transitionName),androidx.core.util.Pair(studentAppName, studentAppName.transitionName))
                    startActivityForResult(it,0,options.toBundle())
                }
        }
        typeWriter.startAnimation()
        finishActivity(0)
    }
}
