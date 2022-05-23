package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.browser.IdCounter
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label

external interface RangeInputProps : Props {
    var title: String
    var value: Int
    var min: Int?
    var max: Int?
    var onValueChanged: (Int) -> Unit
}

val RangeInput = FC<RangeInputProps> { props ->
    val idx = IdCounter.nextId()

    div {
        label {
            +"Number of columns"
        }
        input {
            type = InputType.range
            value = props.value.toString()
            min = (props.min ?: 0).toDouble()
            max = (props.max ?: 24).toDouble()
            onChange = {
                props.onValueChanged(it.target.value.toInt())
            }
        }
    }
}
