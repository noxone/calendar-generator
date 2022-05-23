package org.olafneumann.calendargenerator.components

import org.olafneumann.calendargenerator.browser.IdCounter
import react.FC
import react.Props
import react.dom.aria.ariaDescribedBy
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span

external interface TextInputProps : Props {
    var title: String
    var placeholder: String?
    var value: String
    var onValueChanged: (String) -> Unit
}


val TextInput = FC<TextInputProps> { props ->
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
            type = InputType.text
            props.placeholder?.let { placeholder = it }
            ariaDescribedBy = idx
            value = props.value
            onChange = {
                props.onValueChanged(it.target.value)
            }
        }
    }
}