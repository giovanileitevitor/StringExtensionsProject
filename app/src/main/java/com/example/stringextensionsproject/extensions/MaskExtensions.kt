package com.example.stringextensionsproject.extensions

import java.text.NumberFormat
import java.util.*

/**
 * Extensao para aplicaçao de máscaras genéricas
 * input: String (0..9 - a..X)
 * output: String com a máscara aplicada
 * obs: método nao faz verificaçao se a string possui letras ou numeros
 */
fun String.applyMask(mask: String): String {
    var oldString = this
    for (i in 0 until mask.length) {
        if (mask[i] != '#') {
            oldString = if (mask[i] == '*') {
                val stringBuilder = StringBuilder(oldString)
                stringBuilder.setCharAt(i, mask[i])
                stringBuilder.toString()
            } else {
                StringBuilder(oldString).insert(i, mask[i]).toString()
            }
        }
    }
    return oldString
}

/**
 * Extensao para aplicacao de máscara monetária (Real R$)
 * input: string (0..9)
 * ouput: string com a mascara aplicada (
 */
fun String.applyMoneyMask(): String {
    var formattedValue = replace("[^0-9]".toRegex(), "")
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    formattedValue = try {
        val value = java.lang.Double.parseDouble(formattedValue) / 100
        numberFormat.format(value)
    } catch (e: Exception) {
        numberFormat.format(0.0)
    }

    return formattedValue
}