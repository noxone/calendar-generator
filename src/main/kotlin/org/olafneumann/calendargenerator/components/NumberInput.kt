package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.browser.IdCounter
import org.olafneumann.calendargenerator.i18n.translate
import react.FC
import react.Props
import react.dom.aria.ariaDescribedBy
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
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
        label {
            className = "input-group-text"
            htmlFor = idx
            +props.title.translate()
        }
        input {
            id = idx
            className = "form-control"
            type = InputType.number
            props.placeholder?.let { placeholder = it }
            value = props.value.toString()
            onChange = {
                props.onValueChanged(it.target.value.toInt())
            }
        }
    }
}