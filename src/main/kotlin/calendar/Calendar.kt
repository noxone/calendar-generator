package calendar

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number

class Calendar private constructor(
    val units: List<Unit>
) {
    val maxDaysInUnit = units.maxOf { it.days.size }

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
            val holidayManager = HolidayManager.forCountry(specs.holidayLanguage!! /* FIXME: optional value! */)

            val units = (0 until specs.numItems).toList()
                .map { startMonth + it }
                .map {
                    val month = Month(it.toMonth())
                    Unit(
                        baseType = BaseType.MONTH,
                        year = startYear + it / MONTHS_PER_YEAR,
                        number = month.number,
                        name = month.readableName()
                    )
                }

            units.forEach { unit ->
                val month = Month(unit.number)
                val holidays = holidayManager.getHolidays(unit.year)
                val daysInMonth = DateHelper.daysInMonth(year = unit.year, month = month.number)

                unit.mutableDays += (1..daysInMonth).toList()
                    .map { LocalDate(year = unit.year, month = month, dayOfMonth = it) }
                    .map { date ->
                        val holiday = holidays.firstOrNull { holiday -> holiday.local == date }
                        val info = if (holiday != null && holiday.type == HolidayType.observance) { holiday.name } else { null }
                        val holidayName = if (holiday != null && holiday.type != HolidayType.observance) { holiday.name } else { null }
                        val day = Day(
                            date = date,
                            publicHoliday = holidayName != null,
                            holidayName = holidayName,
                            info = info
                        )
                        day
                    }

            }

            return Calendar(units)

        }
    }

    enum class BaseType(
        val maxNumberOfUnits: Int
    ) {
        YEAR(366),
        MONTH(31),
        WEEK(7)
    }


    class Unit internal constructor(
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
        val date: LocalDate,
        val publicHoliday: Boolean = false,
        val holidayName: String? = null,
        val info: String? = null
    ) {
        val display: String get() = date.dayOfMonth.toString()
        val dayOfWeek = date.dayOfWeek
        val weekend: Boolean = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }
}
