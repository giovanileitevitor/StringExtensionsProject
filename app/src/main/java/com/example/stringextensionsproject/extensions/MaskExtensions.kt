package com.example.stringextensionsproject.extensions

import java.text.NumberFormat
import java.util.*

/**
 * Extensao para aplicaçao de máscaras genéricas
 * input: String
 * output: String with mask applied
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