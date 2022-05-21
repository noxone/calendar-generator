package calendar

import kotlinx.datetime.*

object CalendarHelper {
    val clock = Clock.System.now()

    val currentMonth: Int
        get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).month.number

    fun getMonthName(number: Int): String
        = Month(number).readableName()

    private val allMonths: List<Month> = Month.values().asList()
    val allMonthNames: List<String> = allMonths.map { getMonthName(it.number) }
    val allMonthIndices: List<Int> = allMonths.map { it.number }

    fun daysInMonth(month: Int, year: Int): Int {
        try {
            // console.log(month, year)
            val start = LocalDate(year, month, 1)
            val end = start.plus(DatePeriod(months = 1))
            return start.daysUntil(end)
        } catch (e: Exception) {
            console.error(e)
            return 16
        }
    }


    private fun Month.readableName() = this.name.lowercase().replaceFirstChar { it.uppercase() }
}
