package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.calendar.Calendar
import org.olafneumann.calendargenerator.calendar.CalendarSpecification
import org.olafneumann.calendargenerator.calendar.weekOfYear
import csstype.FontWeight
import csstype.px
import csstype.rgb
import org.olafneumann.calendargenerator.i18n.translate
import kotlinx.datetime.DayOfWeek
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.small
import react.dom.html.ReactHTML.span
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

    div {
        className = "cg-calendar"

        div {
            className = "cg-calendar-header"
            +"Calendar starting ${props.calendarSpecs.startDate.year}"
        }

        div {
            className = "cg-calendar-content"

            div {
                className = "cg-calendar-units"

                val maxDays = calendar.timeUnits.maxOf { it.days.size }
                for (unit in calendar.timeUnits) {
                    div {
                        className = "cg-calendar-unit"

                        div {
                            className = "cg-calendar-unit-header"
                            +unit.name.translate()
                        }

                        for (day in unit.days) {
                            div {
                                className = "cg-calendar-day ${if (day.weekend) "cg-weekend" else ""} ${if (day.publicHoliday) "cg-holiday" else ""}"
                                span {
                                    className = "cg-day-of-month"
                                    +day.date.dayOfMonth.toString().translate()
                                }
                                day.holidayName?.let {
                                    span {
                                        className = "cg-holiday"
                                        +it.translate()
                                    }
                                }
                                if (day.shouldShowCalendarWeekNumber) {
                                    span {
                                        className = "cg-week-number"
                                        +day.date.weekOfYear.toString().translate()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

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
            tr {
                for (unit in calendar.timeUnits) {
                    th {
                        +unit.name.translate()
                    }
                }
            }
        }

        tbody {
            for (dayInMonth in 1..calendar.maxDaysInUnit) {
                tr {
                    for (unit in calendar.timeUnits) {
                        val day = unit.get(dayInMonth)
                        if (day != null) {
                            td {
                                css {
                                    borderWidth = 1.px
                                    borderColor = rgb(0, 0, 0)
                                    if (day.publicHoliday) {
                                        backgroundColor = rgb(200, 200, 200)
                                    } else if (day.weekend) {
                                        backgroundColor = rgb(220, 220, 220)
                                    }
                                    if (day.dayOfWeek == DayOfWeek.SUNDAY) {
                                        fontWeight = FontWeight.bold
                                    }
                                }
                                +day.display
                                day.holidayName?.let {
                                    small {
                                        +it.translate()
                                    }
                                }
                                if (day.dayOfWeek == DayOfWeek.MONDAY) {
                                    small {
                                        +day.date.weekOfYear.toString()
                                    }
                                }
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
