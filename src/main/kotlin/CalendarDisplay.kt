import calendar.CalendarHelper
import react.FC
import react.Props
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr

external interface CalendarDisplayProps : Props {
    var calendarConfig: CalendarConfig
}

private class DisplayMonth(
    val index: Int,
    val year: Int
) {
    val name: String = CalendarHelper.getMonthName(index)
    val numberOfDays = CalendarHelper.daysInMonth(index, year)
}

val CalendarDisplay = FC<CalendarDisplayProps> { props ->
    fun Int.toMonth(): Int = (this - 1) % 12 + 1

    val config = props.calendarConfig
    val months = (config.startMonth .. (config.startMonth + config.numItems))
        .toList()
        .map { DisplayMonth(index = it.toMonth(), year = config.year + it / 12) }

    h2 {
        +"Calendar starting ${props.calendarConfig.year}"
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
                for (month in months) {
                    td {
                        +"${month.name}'${month.year.toString().substring(2)}"
                    }
                }
            }
            for (dayInMonth in 1..31) {
                tr {
                    for (month in months) {
                        val dayIsValid = month.numberOfDays >= dayInMonth
                        td {
                            if (dayIsValid) {
                                +dayInMonth.toString()
                            }
                        }
                    }
                }
            }
        }
    }
}
