package com.example.stringextensionsproject.view

import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stringextensionsproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        initView()
        initListeners()
    }

    private fun initView(){

    }

    private fun initListeners(){
        btn_ext01.setOnClickListener {
            val intent = Intent(this, Extension01Activity::class.java)
            startActivity(intent)
        }

        btn_ext02.setOnClickListener{
            val intent = Intent(this, Extension02Activity::class.java)
            startActivity(intent)
        }

        btn_ext03.setOnClickListener{
            Toast.makeText(this, "Função em desenvolvimento", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        count += 1
        Toast.makeText(this, "Pressione Voltar 2x para encerrar o app", Toast.LENGTH_SHORT).show()

        if(count > 1){
            super.onBackPressed()
            finish()
            count = 0
        }
    }

}
