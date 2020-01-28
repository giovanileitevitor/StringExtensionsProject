package com.example.stringextensionsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stringextensionsproject.R
import com.example.stringextensionsproject.extensions.Mask
import kotlinx.android.synthetic.main.activity_extension02.*

class Extension02Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension02)

        initView()
        initListeners()
    }

    private fun initView(){
        val cpf_mask_format : String = "###.###.###-##"
        edt_input_cpf.addTextChangedListener(Mask.mask(cpf_mask_format, edt_input_cpf))

        val cnpj_mask_format : String = "##.###.###/####-##"
        edt_input_cnpj.addTextChangedListener(Mask.mask(cnpj_mask_format, edt_input_cnpj))
    }

    private fun initListeners(){
        btn_transformar_cpf.setOnClickListener{
            if(validateCPF(edt_input_cpf.text.toString())){
                txt_output_data.setText("OK")
                txt_output_data.background = getDrawable(R.drawable.rounded_corner_aprovado)
            }
            else if(edt_input_cpf.text.isNullOrEmpty()){
                txt_output_data.setText("ERRO")
                txt_output_data.background = getDrawable(R.drawable.rounded_corner_reprovado)
                Toast.makeText(this, "Dados de entrada inválidos ou nulo", Toast.LENGTH_SHORT).show()
            }
        }

        btn_transformar_cnpj.setOnClickListener{
            if(validateCNPJ(edt_input_cnpj.text.toString())){
                txt_output_data_cnpj.setText("OK")
                txt_output_data_cnpj.background = getDrawable(R.drawable.rounded_corner_aprovado)
            }
            else {
                txt_output_data_cnpj.setText("ERRO")
                txt_output_data_cnpj.background = getDrawable(R.drawable.rounded_corner_reprovado)
                Toast.makeText(this, "Dados de entrada inválidos ou nulo", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun validateCPF(cpf : String) : Boolean {
        val cpfClean = cpf.replace(".", "").replace("-", "")

        //## check if size is eleven
        if (cpfClean.length != 11)
            return false

        //## check if is number
        try {
            val number  = cpfClean.toLong()
        }catch (e : Exception){
            return false
        }

        //continue
        var dvCurrent10 = cpfClean.substring(9,10).toInt()
        var dvCurrent11= cpfClean.substring(10,11).toInt()

        //the sum of the nine first digits determines the tenth digit
        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0 ) {
            cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
            i--
        }
        //multiple the nine digits for your weights: 10,9..2
        var sumProductNine = IntArray(9)
        var weight = 10
        var position = 0
        while (weight >= 2){
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }
        //Verify the nineth digit
        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
        if(dvForTenthDigit > 9)
            dvForTenthDigit = 0
        if (dvForTenthDigit != dvCurrent10)
            return false

        //### verify tenth digit
        var cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10
        //multiple the nine digits for your weights: 10,9..2
        var sumProductTen = IntArray(10)
        var w = 11
        var p = 0
        while (w >= 2){
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }
        //Verify the nineth digit
        var dvForeleventhDigit = sumProductTen.sum() % 11
        dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
        if(dvForeleventhDigit > 9)
            dvForeleventhDigit = 0
        if (dvForeleventhDigit != dvCurrent11)
            return false

        return true
    }

    fun validateCNPJ(et: String): Boolean {
        if(et.isNullOrEmpty()){
            return false
        }
        else{
            var str = et.replace("-", "").replace("/","").replace(".","")
            var calc: Int
            var num = 5
            var sum = 0
            for(x in 0..11) {
                calc = str[x].toString().toInt() * num
                sum += calc
                --num
                if(num == 1) num = 9
            }
            var rest = sum % 11
            var test = 11 - rest
            if(test < 2) test = 0
            if(test != str[12].toString().toInt()) return false
            num = 6
            sum = 0
            for(x in 0..12) {
                calc = str[x].toString().toInt() * num
                sum += calc
                --num
                if(num == 1) num = 9
            }
            rest = sum % 11
            test = 11 - rest
            if(test < 2) test = 0
            if(test != str[13].toString().toInt()) return false
            return true
        }
    }
}
