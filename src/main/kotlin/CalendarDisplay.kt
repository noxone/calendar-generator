import calendar.Calendar
import calendar.CalendarSpecification
import csstype.px
import csstype.rgb
import i18n.translate
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr

external interface CalendarDisplayProps : Props {
    var calendarSpecs: CalendarSpecification
}

val CalendarDisplay = FC<CalendarDisplayProps> { props ->
    val calendar = Calendar.createCalendar(props.calendarSpecs)

    h2 {
        +"Calendar starting ${props.calendarSpecs.startDate.year}"
    }

    table {
        thead {
            tr {
                th {
                    colSpan = props.calendarSpecs.numItems
                    +props.calendarSpecs.title.translate()
                }
            }
        }

        tbody {
            tr {
                for (unit in calendar.units) {
                    td {
                        +unit.name.translate()
                    }
                }
            }
            for (dayInMonth in 1..calendar.maxDaysInUnit) {
                tr {
                    for (unit in calendar.units) {
                        val day = unit.get(dayInMonth)
                        if (day != null) {
                            td {
                                css {
                                    borderWidth = 1.px
                                    borderColor = rgb(0,0,0)
                                    if (day.publicHoliday) {
                                        backgroundColor = rgb(200,200,200)
                                    } else if (day.weekend) {
                                        backgroundColor = rgb(220,220,220)
                                    }
                                }
                                +day.display
                            }
                        } else {
                            td {}
                        }
                    }
                }
            }
        }
    }
}
