package calendar

import kotlinx.datetime.LocalDate
import react.State

data class CalendarSpecification(
    var title: String = "Calendar",
    var baseType: CalendarBaseType = CalendarBaseType.MONTH,
    var numItems: Int = DateHelper.allMonths.size,
    var startDate: LocalDate = DateHelper.currentDate
) : State

enum class CalendarBaseType {
    YEAR, MONTH, WEEK
}
