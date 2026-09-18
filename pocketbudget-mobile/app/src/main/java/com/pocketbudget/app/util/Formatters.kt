package com.pocketbudget.app.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private val isoParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
    timeZone = TimeZone.getTimeZone("UTC")
}

private val displayFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

fun nowAsIsoString(): String {
    return isoParser.format(Date())
}

fun formatCurrency(value: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(value)
}

fun formatDate(isoString: String): String {
    return try {
        val date = isoParser.parse(isoString)
        if (date != null) displayFormatter.format(date) else isoString
    } catch (e: Exception) {
        isoString
    }
}