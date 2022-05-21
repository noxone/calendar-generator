import kotlinx.browser.document
import react.create
import react.dom.render

fun main() {
    val root = document.createElement("div")
    root.className = "container-fluid"
    document.body!!.appendChild(root)

    render(CalendarGenerator.create {
        /*title = calendarConfig.title
        onCalendarTitleChanged = {
            calendarConfig.title = it
            title = it
        }*/
    }, root)
}