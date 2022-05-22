package calendar

class Calendar private constructor() {
    companion object {
        fun createCalendar(specs: CalendarSpecification): Calendar {
            return Calendar()
        }
    }

    val months: List<CalendarMonth> = emptyList()

    class CalendarMonth private constructor() {

    }
}