package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.browser.IdCounter
import react.FC
import react.Props
import react.dom.aria.ariaDescribedBy
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span

external interface NumberInputProps : Props {
    var title: String
    var placeholder: String?
    var value: Int
    var onValueChanged: (Int) -> Unit
}


val NumberInput = FC<NumberInputProps> { props ->
    val idx = IdCounter.nextId()

    div {
        className = "input-group"
        span {
            className = "input-group-text"
            id = idx
            +props.title
        }
        input {
            className = "form-control"
            type = InputType.number
            props.placeholder?.let { placeholder = it }
            ariaDescribedBy = idx
            value = props.value.toString()
            onChange = {
                props.onValueChanged(it.target.value.toInt())
            }
        }
    }
}