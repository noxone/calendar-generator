package org.olafneumann.calendargenerator.calendar

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
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
    fun getHolidays(year: Int, language: String = definedExternally): Array<Holiday>
    fun isHoliday(date: Date): Boolean
    fun getLanguages(): dynamic
    fun setLanguages(languages: String)
}

external class Holiday {
    val date: String
    val start: Date
    val end: Date
    val name: String
    var type: String
}

enum class HolidayType {
    public, bank, school, observance, optional
}

val Holiday.local: LocalDate get() = Instant.fromEpochMilliseconds(this.start.getTime().toLong()).toLocalDateTime(TimeZone.currentSystemDefault()).date
val Holiday.holidayType : HolidayType get() = HolidayType.valueOf(type)

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

    fun getHolidays(year: Int) : List<Holiday>
        = holidays.getHolidays(year).asList()
}

