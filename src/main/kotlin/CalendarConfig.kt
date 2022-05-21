import calendar.CalendarHelper
import react.State

data class CalendarConfig(
    var title: String = "Calendar",
    var year: Int = 2022,
    var numItems: Int = 12,
    var startMonth: Int = CalendarHelper.currentMonth
) : State
