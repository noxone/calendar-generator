import calendar.CalendarSpecification
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
                onStartMonthChange = { calendarSpecs = calendarSpecs.copy(startMonth = it) }
                onYearChanged = { calendarSpecs = calendarSpecs.copy(year = it)}
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