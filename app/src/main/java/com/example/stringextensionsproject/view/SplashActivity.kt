package com.example.stringextensionsproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.bumptech.glide.Glide
import com.example.stringextensionsproject.R
import kotlinx.android.synthetic.main.activity_extension01.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000
    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this).load(R.drawable.fundotela01).into(image_fundo)

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
