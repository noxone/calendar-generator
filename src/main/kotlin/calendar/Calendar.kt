package calendar

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number

class Calendar private constructor(
    val months: List<Unit>
) {
    companion object {
        private const val MONTHS_PER_YEAR = 12
        private const val WEEKS_PER_YEAR = 52

        private fun Int.toMod(mod: Int) = (this - 1) % mod + 1
        private fun Int.toMonth(): Int = toMod(MONTHS_PER_YEAR)
        private fun Int.toWeek(): Int = toMod(WEEKS_PER_YEAR)

        fun createCalendar(specs: CalendarSpecification): Calendar
            = when (specs.baseType) {
                BaseType.MONTH -> createMonthlyCalendar(specs = specs)
                else -> throw Exception("Not yet implemented")
            }

        private fun createMonthlyCalendar(specs: CalendarSpecification): Calendar {
            val startYear = specs.startDate.year
            val startMonth = specs.startDate.monthNumber

            val units = (1..specs.numItems).toList()
                .map { startMonth + it }
                .map { Unit(baseType = BaseType.MONTH, year = startYear + it / MONTHS_PER_YEAR, number = it.toMonth()) }

            units.forEach { unit ->
                val month = Month(unit.number)
                val daysInMonth = DateHelper.daysInMonth(year = unit.year, month = month.number)

                unit.mutableDays += (1..daysInMonth).toList()
                    .map { LocalDate(year = unit.year, month = month, dayOfMonth = it) }
                    .map { Day(date = it) }
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
        val number: Int
    ) {
        internal val mutableDays: MutableList<Day> = mutableListOf()
        val days: List<Day> get() = mutableDays
    }

    class Day internal constructor(
        val date: LocalDate
    ) {
        val display: String get() = date.dayOfMonth.toString()
    }
}
