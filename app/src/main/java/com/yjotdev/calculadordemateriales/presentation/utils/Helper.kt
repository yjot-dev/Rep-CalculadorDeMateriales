package com.yjotdev.calculadordemateriales.presentation.utils

object Helper {
    fun isValidNumber(input: String): Boolean{
        return Regex("^[0-9]+(\\.[0-9]+)?$").matches(input)
    }
}