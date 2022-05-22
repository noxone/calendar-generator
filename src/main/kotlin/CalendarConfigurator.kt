import calendar.CalendarSpecification
import calendar.DateHelper
import components.IntDropDownInput
import components.NumberInput
import components.RangeInput
import components.TextInput
import react.FC
import react.Props
import react.dom.html.ReactHTML.h2

external interface CalendarConfigurationProps : Props {
    var calendarSpecs: CalendarSpecification
    var onTitleChanged: (String) -> Unit
    var onNumItemsChange: (Int) -> Unit
    var onStartMonthChange: (Int) -> Unit
    var onYearChanged: (Int) -> Unit
}

val CalendarConfiguration = FC<CalendarConfigurationProps> { props ->
    h2 {
        +"Configuration"
    }

    TextInput {
        title = "Title"
        onValueChanged = { props.onTitleChanged(it) }
        value = props.calendarSpecs.title
    }
    RangeInput {
        title = "Columns"
        value = props.calendarSpecs.numItems
        min = 1
        max = 24
        onValueChanged = { props.onNumItemsChange(it) }
    }
    NumberInput {
        title = "Year"
        value = props.calendarSpecs.startDate.year
        onValueChanged = { props.onYearChanged(it) }
    }
    IntDropDownInput {
        title = "First month"
        items = DateHelper.allMonthIndices
        value = props.calendarSpecs.startDate.monthNumber
        onValueChanged = { props.onStartMonthChange(it) }
        toString = { DateHelper.getMonthName(it) }
    }
}