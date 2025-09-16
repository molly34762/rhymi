package com.rhymi.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateExtensions {
    fun convertMillisToDate(
        millis: Long,
        pattern: String = "MM/dd/yyyy",
        locale: Locale = Locale.getDefault()
    ): String {
        val formatter = SimpleDateFormat(pattern, locale)
        return formatter.format(Date(millis))
    }
}