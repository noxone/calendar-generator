package org.olafneumann.calendargenerator.calendar

import kotlinx.datetime.LocalDate
import react.State
import org.olafneumann.calendargenerator.calendar.Calendar.BaseType
import org.olafneumann.calendargenerator.browser.userRegion

data class CalendarSpecification(
    var title: String = "Calendar",
    var baseType: BaseType = BaseType.MONTH,
    var numItems: Int = DateHelper.allMonths.size,
    var startDate: LocalDate = DateHelper.currentDate,
    var holidayRegion: String? = userRegion
) : State
