package org.olafneumann.calendargenerator

import org.olafneumann.calendargenerator.calendar.HolidayManager
import kotlinx.browser.document
import kotlinx.datetime.Clock
import org.olafneumann.calendargenerator.browser.userRegion
import org.olafneumann.calendargenerator.components.CalendarGenerator
import react.create
import react.dom.render

fun main() {
    val root = document.createElement("div")
    root.className = "container-fluid"
    document.body!!.appendChild(root)

    console.info("Current time:", Clock.System.now().toString())
    console.info("User region:", userRegion)
    console.info("Available holiday regions: ", HolidayManager.countryCodes.joinToString(separator = ", "))

    render(CalendarGenerator.create {
        /*title = calendarConfig.title
        onCalendarTitleChanged = {
            calendarConfig.title = it
            title = it
        }*/
    }, root)
}