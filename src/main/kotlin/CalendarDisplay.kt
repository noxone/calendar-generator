import react.FC
import react.Props
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.useState

external interface CalendarDisplayProps : Props {
    var calendarConfig: CalendarConfig
}

val CalendarDisplay = FC<CalendarDisplayProps> { props ->
    h2 {
        +"Preview"
    }
    table {
        thead {
            tr {
                th {
                    colSpan = props.calendarConfig.numItems
                    +"${props.calendarConfig.title}:${props.calendarConfig.numItems}"
                }
            }
        }
        tbody {
            tr {
                for (i in 1..props.calendarConfig.numItems) {
                    td {
                        +"column $i"
                    }
                }
            }
        }
    }
}