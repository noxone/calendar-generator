package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.calendar.CalendarSpecification
import org.olafneumann.calendargenerator.calendar.DateHelper
import org.olafneumann.calendargenerator.calendar.HolidayManager
import kotlinx.datetime.Month
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2

external interface CalendarConfigurationProps : Props {
    var calendarSpecs: CalendarSpecification
    var onTitleChanged: (String) -> Unit
    var onNumItemsChange: (Int) -> Unit
    var onStartMonthChange: (Month) -> Unit
    var onYearChanged: (Int) -> Unit
    var onHolidayLanguageChanged: (String) -> Unit
}

val CalendarConfiguration = FC<CalendarConfigurationProps> { props ->
    h2 {
        +"Configuration"
    }

    div {
        className = "row"

        div {
            className = "col-2"
            TextInput {
                title = "Title"
                onValueChanged = { props.onTitleChanged(it) }
                value = props.calendarSpecs.title
            }
        }
        div {
            className = "col-2"
            RangeInput {
                title = "Columns"
                value = props.calendarSpecs.numItems
                min = 1
                max = 24
                onValueChanged = { props.onNumItemsChange(it) }
            }
        }
        div {
            className = "col-2"
            NumberInput {
                title = "Year"
                value = props.calendarSpecs.startDate.year
                onValueChanged = { props.onYearChanged(it) }
            }
        }
        div {
            className = "col-2"
            DropDownInput {
                title = "First month"
                items = DateHelper.allMonths
                value = props.calendarSpecs.startDate.month
                onValueChanged = { props.onStartMonthChange(it as Month) }
                toString = { (it as Month).name }
            }
        }
        div {
            className = "col-2"
            DropDownInput {
                title = "Holidays"
                items = HolidayManager.countries.entries.toList()
                value = HolidayManager.countries.entries.firstOrNull { it.key == props.calendarSpecs.holidayRegion }
                onValueChanged = { props.onHolidayLanguageChanged((it as Map.Entry<String, String>).key) }
                toString = { (it as Map.Entry<String, String>).value }
            }
        }
    }
}