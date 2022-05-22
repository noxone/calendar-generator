import calendar.CalendarSpecification
import kotlinx.datetime.LocalDate
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.useState

/*external interface GeneratorProps : Props {
    var title: String
    var onCalendarTitleChanged: (String) -> Unit
}*/

val CalendarGenerator = FC<Props> {
    var calendarSpecs by useState(CalendarSpecification())

    h1 {
        +"Calender Generator"
    }
    div {
        className = "row"
        div {
            className = "col-3"
            CalendarConfiguration {
                this.calendarSpecs = calendarSpecs
                onTitleChanged = { calendarSpecs = calendarSpecs.copy(title = it) }
                onNumItemsChange = { calendarSpecs = calendarSpecs.copy(numItems = it) }
                onStartMonthChange = {
                    calendarSpecs = calendarSpecs.copy(startDate = LocalDate(
                        year = calendarSpecs.startDate.year,
                        monthNumber = it,
                        dayOfMonth = calendarSpecs.startDate.dayOfMonth))
                }
                onYearChanged = {
                    calendarSpecs = calendarSpecs.copy(startDate = LocalDate(
                        year = it,
                        monthNumber = calendarSpecs.startDate.monthNumber,
                        dayOfMonth = calendarSpecs.startDate.dayOfMonth))
                }
            }
        }
        div {
            className = "col-9"
            CalendarDisplay {
                this.calendarSpecs = calendarSpecs
            }
        }
    }
}