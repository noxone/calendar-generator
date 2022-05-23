package org.olafneumann.calendargenerator.calendar

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class DateHelperTest {
    @Test
    fun testLeapYears() {
        val years = listOf(      17, 1600,  1800,  1845,  1900, 1920,  1921,  1922, 1924, 2000, 2004,  2100)
        val expected = listOf(false, true, false, false, false, true, false, false, true, true, true, false)

        val actual = years
            .map { LocalDate(year = it, monthNumber = 2, dayOfMonth = 3) }
            .map { it.isLeapYear }

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testWeeksInYear() {
        val years = listOf( 2003, 2004, 2005, 2006, 2007, 2011, 2012, 2013, 2015, 2024, 2025, 2026)
        val expected = listOf(52,   53,   52,   52,   52,   52,   52,   52,   53,   52,   52,   53)

        val actual = years
            .map { LocalDate(year = it, monthNumber = 5, dayOfMonth = 24) }
            .map { it.weeksInYear }

        assertEquals(expected = expected, actual = actual, message = "abcd")
    }

    private fun testWeekOfYear(year: Int, monthNumber: Int, dayOfMonth: Int, expectedWeek: Int) {
        val given = LocalDate(year = year, monthNumber = monthNumber, dayOfMonth = dayOfMonth)

        val actual = given.weekOfYear

        assertEquals(expected = expectedWeek, actual = actual)
    }

    @Test
    fun testWeekOfYear1() = testWeekOfYear(year = 1977, monthNumber = 1, dayOfMonth = 1, expectedWeek = 53)
    @Test
    fun testWeekOfYear2() = testWeekOfYear(year = 1978, monthNumber = 1, dayOfMonth = 1, expectedWeek = 52)
    @Test
    fun testWeekOfYear3() = testWeekOfYear(year = 1979, monthNumber = 12, dayOfMonth = 31, expectedWeek = 1)
    @Test
    fun testWeekOfYear4() = testWeekOfYear(year = 1980, monthNumber = 1, dayOfMonth = 1, expectedWeek = 1)

    private fun testDaysInMonth(year: Int, monthNumber: Int, expectedDayCount: Int) {
        val actual = DateHelper.daysInMonth(year = year, month = monthNumber)

        assertEquals(expected = expectedDayCount, actual = actual)
    }

    @Test
    fun testDaysInMonth1() = testDaysInMonth(year = 2022, monthNumber = 1, expectedDayCount = 31)
    @Test
    fun testDaysInMonth2() = testDaysInMonth(year = 2023, monthNumber = 2, expectedDayCount = 28)
    @Test
    fun testDaysInMonth3() = testDaysInMonth(year = 2024, monthNumber = 2, expectedDayCount = 29)
    @Test
    fun testDaysInMonth4() = testDaysInMonth(year = 2025, monthNumber = 4, expectedDayCount = 30)
}