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

external interface TextInputProps : Props {
    var title: String
    var placeholder: String?
    var value: String
    var onValueChanged: (String) -> Unit
}


val TextInput = FC<TextInputProps> { props ->
    val idx = IdCounter.nextId()
    val labelIdx = "${idx}-label"

    div {
        className = "input-group"
        label {
            className = "input-group-text"
            htmlFor = idx
            id = labelIdx
            +props.title.translate()
        }
        input {
            id = idx
            className = "form-control"
            type = InputType.text
            props.placeholder?.let { placeholder = it }
            ariaDescribedBy = labelIdx
            value = props.value
            onChange = {
                props.onValueChanged(it.target.value)
            }
        }
    }
}