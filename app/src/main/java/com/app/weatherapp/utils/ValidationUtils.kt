package com.app.weatherapp.utils

import com.app.weatherapp.BuildConfig
import java.util.regex.Pattern

val units = arrayOf("metric", "imperial", "default")
val unitsSymbols = arrayOf("°C", "°F", "K")

fun isZipCodeValid(zipCode: String): Boolean {
    val zipCodePattern = Pattern.compile("\\d{6}")
    return zipCodePattern.matcher(zipCode).matches()
}

fun isAppIdCorrect(appId: String): Boolean = BuildConfig.APP_ID == appId

fun isUnitsCorrect(unit: String): Boolean = units.contains(unit)