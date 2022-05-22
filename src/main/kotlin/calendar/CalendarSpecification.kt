package calendar

import calendar.DateHelper
import react.State

data class CalendarSpecification(
    var title: String = "Calendar",
    var year: Int = DateHelper.currentYear,
    var numItems: Int = DateHelper.allMonths.size,
    var startMonth: Int = DateHelper.currentMonth
) : State