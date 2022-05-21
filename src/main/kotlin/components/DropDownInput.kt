package components

import react.FC
import react.Props
import react.dom.aria.ariaDescribedBy
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.span

external interface IntDropDownInputProps : Props {
    var title: String
    var items: List<Int>
    var value: Int?
    var onValueChanged: (Int) -> Unit
    var toString: (Int) -> String
}

val IntDropDownInput = FC<IntDropDownInputProps> { props ->
    select {
        className = "form-select"
        onChange = { props.onValueChanged(it.target.value.toInt()) }
        value = props.value?.toString()

        for (item in props.items) {
            option {
                value = item.toString()
                +props.toString(item)
            }
        }
    }
}