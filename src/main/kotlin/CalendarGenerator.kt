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
    //var calTitle by useState("abc def")
    //var calItems by useState(2)
    var calendarConfig by useState(CalendarConfig())

    h1 {
        +"Calender Generator"
    }
    div {
        className = "row"
        div {
            className = "col-3"
            CalendarConfiguration {
                this.calendarConfig = calendarConfig
                onTitleChanged = { calendarConfig = calendarConfig.copy(title = it) }
                onNumItemsChange = { calendarConfig = calendarConfig.copy(numItems = it) }
            }
        }
        div {
            className = "col-9"
            CalendarDisplay {
                this.calendarConfig = calendarConfig
            }
        }
    }
}