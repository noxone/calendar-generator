package calendar

import kotlinx.datetime.LocalDate
import react.State
import calendar.Calendar.BaseType

data class CalendarSpecification(
    var title: String = "Calendar",
    var baseType: BaseType = BaseType.MONTH,
    var numItems: Int = DateHelper.allMonths.size,
    var startDate: LocalDate = DateHelper.currentDate
) : State
