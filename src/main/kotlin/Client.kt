import calendar.Holiday
import kotlinx.browser.document
import kotlinx.datetime.Clock
import react.create
import react.dom.render

fun main() {
    val root = document.createElement("div")
    root.className = "container-fluid"
    document.body!!.appendChild(root)

    console.info("Current time:", Clock.System.now().toString())
    console.info("Preferred language:", preferredLanguage)
    console.info("Available holiday regions: ", Holiday.countryCodes.joinToString(separator = ", "))

    render(CalendarGenerator.create {
        /*title = calendarConfig.title
        onCalendarTitleChanged = {
            calendarConfig.title = it
            title = it
        }*/
    }, root)
}