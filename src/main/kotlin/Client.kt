import calendar.Holiday
import kotlinx.browser.document
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import react.create
import react.dom.render

fun main() {
    val root = document.createElement("div")
    root.className = "container-fluid"
    document.body!!.appendChild(root)

    console.info(Clock.System.now().toString())
    //console.log(Holiday.getCountries().keys.joinToString(separator = ", "))
    Holiday.getCountries().entries.forEach { console.log(it.key, it.value) }

    render(CalendarGenerator.create {
        /*title = calendarConfig.title
        onCalendarTitleChanged = {
            calendarConfig.title = it
            title = it
        }*/
    }, root)
}