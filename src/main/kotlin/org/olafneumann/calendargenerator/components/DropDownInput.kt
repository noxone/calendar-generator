package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.i18n.translate
import react.FC
import react.Props
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select

external interface DropDownInputProps<T> : Props {
    var title: String
    var items: List<T>
    var value: T?
    var onValueChanged: (T) -> Unit
    var toString: (T) -> String
}

val DropDownInput = FC<DropDownInputProps<Any>> { props ->
    select {
        className = "form-select"
        onChange = { props.onValueChanged(props.items[it.target.value.toInt()]) }
        value = props.value?.let { props.items.indexOf(it).toString() }

        for (item in props.items) {
            option {
                value = props.items.indexOf(item).toString()
                +props.toString(item).translate()
            }
        }
    }
}
