package calendar

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic

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
    fun getCountries(): dynamic
}

class Holiday {
    companion object {
        private val holidays = Holidays()
        private val holidayCountries = holidays.getCountries()

        init {
            //console.log(holidayCountries)
        }

        val countries: Map<String, String> = Json.decodeFromDynamic(holidayCountries)
        val countryCodes: Collection<String> = countries.keys
    }



    fun getCountries() = Json.decodeFromDynamic<Map<String, JSON>>(holidays.getCountries())


}
