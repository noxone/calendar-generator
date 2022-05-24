package org.olafneumann.calendargenerator.calendar

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number

class Calendar private constructor(
    val timeUnits: List<TimeUnit>
) {
    val maxDaysInUnit = timeUnits.maxOf { it.days.size }

    companion object {
        private const val MONTHS_PER_YEAR = 12
        private const val WEEKS_PER_YEAR = 52

        private fun Int.toMod(mod: Int) = (this - 1) % mod + 1
        private fun Int.toMonth(): Int = toMod(MONTHS_PER_YEAR)
        private fun Int.toWeek(): Int = toMod(WEEKS_PER_YEAR)

        fun createCalendar(specs: CalendarSpecification): Calendar {
            try {
                return when (specs.baseType) {
                    BaseType.MONTH -> createMonthlyCalendar(specs = specs)
                    else -> throw Exception("Not yet implemented")
                }
            } catch (e: Exception) {
                console.error(e)
                throw Exception("nö", e)
            }
        }

        private fun createMonthlyCalendar(specs: CalendarSpecification): Calendar {
            val startYear = specs.startDate.year
            val startMonth = specs.startDate.monthNumber
            val holidayManager = specs.holidayRegion?.let { HolidayManager.forCountry(it) }

            val timeUnits = (0 until specs.numItems).toList()
                .map { startMonth + it }
                .map { monthNumber ->
                    val month = Month(monthNumber.toMonth())
                    TimeUnit(
                        baseType = BaseType.MONTH,
                        year = startYear + (monthNumber - 1) / MONTHS_PER_YEAR,
                        number = month.number,
                        name = month.readableName()
                    )
                }

            val holidayMap = mutableMapOf<Int, List<Holiday>>()

            timeUnits.forEach { timeUnit ->
                val month = Month(timeUnit.number)
                val holidays = holidayMap.getOrPut(timeUnit.year) { holidayManager?.getHolidays(timeUnit.year) ?: emptyList() }
                val daysInMonth = DateHelper.daysInMonth(year = timeUnit.year, month = month.number)

                timeUnit.mutableDays += (1..daysInMonth).toList()
                    .map { LocalDate(year = timeUnit.year, month = month, dayOfMonth = it) }
                    .map { date ->
                        val holiday = holidays.firstOrNull { holiday -> holiday.local == date }
                        val info = if (holiday != null && holiday.holidayType == HolidayType.observance) { holiday.name } else { null }
                        val holidayName = if (holiday != null && holiday.holidayType != HolidayType.observance) { holiday.name } else { null }
                        val day = Day(
                            parent = timeUnit,
                            date = date,
                            publicHoliday = holidayName != null,
                            holidayName = holidayName,
                            info = info
                        )
                        day
                    }
            }
            return Calendar(timeUnits)
        }
    }

    enum class BaseType(
        val maxNumberOfUnits: Int
    ) {
        YEAR(366),
        MONTH(31),
        WEEK(7)
    }


    class TimeUnit internal constructor(
        val baseType: BaseType,
        val year: Int,
        val number: Int,
        val name: String
    ) {
        internal val mutableDays: MutableList<Day> = mutableListOf()
        val days: List<Day> get() = mutableDays

        fun get(index: Int): Day? = days.getOrNull(index - 1)
    }

    class Day internal constructor(
        val parent: TimeUnit,
        val date: LocalDate,
        val publicHoliday: Boolean = false,
        val holidayName: String? = null,
        val info: String? = null
    ) {
        val display: String get() = date.dayOfMonth.toString()
        val dayOfWeek = date.dayOfWeek
        val weekend: Boolean = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

        val shouldShowCalendarWeekNumber: Boolean =
            dayOfWeek == DayOfWeek.MONDAY /*&& this != parent.days.last()
                    || dayOfWeek == DayOfWeek.TUESDAY && this == parent.days.first()*/
    }
}
