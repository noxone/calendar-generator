package org.olafneumann.calendargenerator.calendar

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic
import kotlin.js.Date

@JsModule("date-holidays")
@JsNonModule
private external class Holidays(
    country: String = definedExternally, //@param {String|Object} country - if object use `{ country: {String}, state: {String}, region: {String} }`
    state: String = definedExternally, //* @param {String} [state] - specifies state
    region: String = definedExternally, //* @param {String} [region] - specifies region
    opts: Any = definedExternally //* @param {Object} [opts] - options
    //* @param {Array|String} opts.languages - set language(s) with ISO 639-1 shortcodes
    //* @param {String} opts.timezone - set timezone
    //* @param {Array} opts.types - holiday types to consider
) {
    fun getCountries(language: String = definedExternally): dynamic
    fun getHolidays(year: Int, language: String = definedExternally): dynamic
    fun isHoliday(date: Date): Boolean
    fun getLanguages(): dynamic
    fun setLanguages(languages: String)
}

class Holiday(
    val date: String,
    val start: Date,
    val end: Date,
    val name: String,
    var type: HolidayType
) {
    val local = Instant.fromEpochMilliseconds(start.getTime().toLong()).toLocalDateTime(TimeZone.currentSystemDefault()).date
}

enum class HolidayType {
    public, bank, school, observance, optional
}

class HolidayManager private constructor(
    private val holidays: Holidays
) {
    companion object {
        private val holidays = Holidays()
        private val holidayCountries = holidays.getCountries()

        val countries: Map<String, String> = Json.decodeFromDynamic(holidayCountries)
        val countryCodes: Collection<String> = countries.keys

        fun forCountry(code: String): HolidayManager {
            return HolidayManager(Holidays(country = code))
        }
    }

    fun getHolidays(year: Int) : List<Holiday> {
        val list = holidays.getHolidays(year = year)
        val length: Int = list.length as Int
        val resultList = mutableListOf<Holiday>()
        for (index in 0 until length) {
            val item = list[index]
            val holiday = Holiday(
                date = item.date as String,
                start = item.start as Date,
                end = item.end as Date,
                name = item.name as String,
                type = HolidayType.valueOf(item.type as String)
            )
            resultList += holiday
        }
        return resultList
    }
}

