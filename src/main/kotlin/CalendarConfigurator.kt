import calendar.CalendarHelper
import components.IntDropDownInput
import components.NumberInput
import components.RangeInput
import components.TextInput
import react.FC
import react.Props
import react.dom.html.ReactHTML.h2

external interface CalendarConfigurationProps : Props {
    var calendarConfig: CalendarConfig
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
        value = props.calendarConfig.title
    }
    RangeInput {
        title = "Columns"
        value = props.calendarConfig.numItems
        min = 1
        max = 24
        onValueChanged = { props.onNumItemsChange(it) }
    }
    NumberInput {
        title = "Year"
        value = props.calendarConfig.year
        onValueChanged = { props.onYearChanged(it) }
    }
    IntDropDownInput {
        title = "First month"
        items = CalendarHelper.allMonthIndices
        value = props.calendarConfig.startMonth
        onValueChanged = { props.onStartMonthChange(it) }
        toString = { CalendarHelper.getMonthName(it) }
    }
}