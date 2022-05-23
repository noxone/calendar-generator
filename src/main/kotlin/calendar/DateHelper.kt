package calendar

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object DateHelper {
    val clock = Clock.System.now()

    val currentDate: LocalDate
        get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    fun getMonthName(number: Int): String = Month(number).readableName()

    val allMonths: List<Month> = Month.values().asList()
    val allMonthNames: List<String> = allMonths.map { getMonthName(it.number) }
    val allMonthIndices: List<Int> = allMonths.map { it.number }

    fun daysInMonth(year: Int, month: Int): Int {
        return try {
            val start = LocalDate(year, month, dayOfMonth = 1)
            val end = start.plus(DatePeriod(months = 1))
            start.daysUntil(end)
        } catch (e: Exception) {
            console.error(e)
            16
        }
    }
}

fun Month.readableName() = this.name.lowercase().replaceFirstChar { it.uppercase() }

// https://en.wikipedia.org/wiki/ISO_week_date#Calculating_the_week_number_from_an_ordinal_date
val LocalDate.weekOfYear: Int
    get() {
        val week = (dayOfYear - dayOfWeek.isoDayNumber + 10) / 7
        return if (week < 1) {
            minus(1, DateTimeUnit.DAY).weeksInYear
        } else if (week > weeksInYear) {
            1
        } else {
            week
        }
    }

// https://www.quora.com/What-year-will-have-53-weeks
/* Those rules can be manipulated to find easier expressions for when a (week) year has 53 weeks. I like the following:
 * If Dec 28 is in a different week than Dec 27 in a common year or Dec 26 in a leap year, the year has 53 weeks.
 * Alternatively, if Dec 28 is a Monday, or in a leap year either Monday or Tuesday, the (week) year has 53 weeks. */
val LocalDate.weeksInYear: Int
    get() {
        val dayOfWeek = LocalDate(year = year, month = Month.DECEMBER, dayOfMonth = 28).dayOfWeek
        return if (dayOfWeek == DayOfWeek.MONDAY || (isLeapYear && dayOfWeek == DayOfWeek.TUESDAY)) {
            53
        } else {
            52
        }
    }

val LocalDate.isLeapYear: Boolean
    get() = (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)
