import components.RangeInput
import components.TextInput
import react.FC
import react.Props
import react.dom.aria.ariaDescribedBy
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.span

external interface CalendarConfigurationProps : Props {
    var calendarConfig: CalendarConfig
    var onTitleChanged: (String) -> Unit
    var onNumItemsChange: (Int) -> Unit
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
}