package com.example.stringextensionsproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.stringextensionsproject.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000
    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myHandler = Handler()
        myHandler.postDelayed(
            {
                gotoLoginActivity()
            },
            SPLASH_TIME_OUT
        )
    }

    private fun gotoLoginActivity(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
