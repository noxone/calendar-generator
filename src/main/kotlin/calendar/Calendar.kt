package calendar

class Calendar private constructor(
    val months: List<CalendarMonth>
) {
    companion object {
        fun createCalendar(specs: CalendarSpecification): Calendar {
            1..specs.numItems

            return Calendar(emptyList())
        }
    }

    class CalendarMonth private constructor(

    ) {

    }
}