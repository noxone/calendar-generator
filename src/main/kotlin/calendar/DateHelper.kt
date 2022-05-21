package calendar

import kotlinx.datetime.*

object DateHelper {
    val clock = Clock.System.now()

    val currentMonth: Int
        get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).month.number
    val currentYear: Int
        get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year

    fun getMonthName(number: Int): String
        = Month(number).readableName()

    val allMonths: List<Month> = Month.values().asList()
    val allMonthNames: List<String> = allMonths.map { getMonthName(it.number) }
    val allMonthIndices: List<Int> = allMonths.map { it.number }

    fun daysInMonth(year: Int, month: Int): Int {
        try {
            val start = LocalDate(year, month, 1)
            val end = start.plus(DatePeriod(months = 1))
            return start.daysUntil(end)
        } catch (e: Exception) {
            console.error(e)
            return 16
        }
    }

    fun weekday(year: Int, month: Int, day: Int): DayOfWeek
        = LocalDate(year = year, month = Month(month), dayOfMonth = day)
            .dayOfWeek


    private fun Month.readableName() = this.name.lowercase().replaceFirstChar { it.uppercase() }
}
