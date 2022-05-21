import calendar.DateHelper
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
    var calendarConfig: CalendarConfig
}

private class DisplayMonth(
    val index: Int,
    val year: Int
) {
    val name: String = DateHelper.getMonthName(index)
    val numberOfDays = DateHelper.daysInMonth(year, index)
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
                    +props.calendarConfig.title.translate()
                }
            }
        }

        tbody {
            tr {
                for (month in months) {
                    td {
                        +month.name.translate()
                    }
                }
            }
            for (dayInMonth in 1..31) {
                tr {
                    for (month in months) {
                        val dayIsValid = month.numberOfDays >= dayInMonth
                        td {
                            css {
                                borderWidth = 1.px
                                borderColor = rgb(0,0,0)
                            }
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
