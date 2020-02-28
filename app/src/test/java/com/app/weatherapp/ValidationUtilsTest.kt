package com.app.weatherapp

import com.app.weatherapp.utils.isAppIdCorrect
import com.app.weatherapp.utils.isUnitsCorrect
import com.app.weatherapp.utils.isZipCodeValid
import org.junit.Assert
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun test_IsZipCodeValid() {
        val actual = isZipCodeValid("123456")

        Assert.assertEquals(true, actual)
    }

    @Test
    fun test_IsAppIdValid() {
        val actual = isAppIdCorrect(BuildConfig.APP_ID)

        Assert.assertEquals(true, actual)
    }

    @Test
    fun test_IsUnitsCorrect() {
        val actual = isUnitsCorrect("123456")

        Assert.assertEquals(true, actual)
    }
}