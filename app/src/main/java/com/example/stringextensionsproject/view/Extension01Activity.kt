package com.example.stringextensionsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stringextensionsproject.R
import com.example.stringextensionsproject.extensions.*
import kotlinx.android.synthetic.main.activity_extension01.*

class Extension01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension01)

        initView()
        initListeners()
    }

    private fun initView(){

    }

    private fun initListeners(){
        btn_transformar.setOnClickListener {
            var inputText : String = edt_input_data.text.toString()
            val masktype = "##-##-####"

            if(inputText.isNullOrEmpty()){
                Toast.makeText(this, "Dados de entrada inválidos ou nulo", Toast.LENGTH_SHORT).show()
                txt_output.setText(" - - - ")
            }
            else{
                txt_output.setText(inputText.applyMask(masktype))
                txt_output.isEnabled = false
            }
        }

        btn_transformar_money.setOnClickListener{
            var inputText : String = edt_input_money.text.toString()

            if(inputText.isNullOrEmpty()){
                Toast.makeText(this, "Dados de entrada inválidos ou nulo", Toast.LENGTH_SHORT).show()
                txt_output_money.setText(" - - - ")
            }
            else{
                txt_output_money.setText(inputText.applyMoneyMask())
                txt_output_money.isEnabled = false
            }
        }

    }

}
