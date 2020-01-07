package com.example.themoviedb.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviedb.R
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        splash_screen_logo.setImageResource(R.mipmap.splash_screen_logo)

        val SPLASH_TIME_OUT = 2000
        val homeIntent = Intent(
            this@SplashActivity,
            MainActivity::class.java
        )

        Handler().postDelayed({
            //Do some stuff here, like implement deep linking
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())

    }
}