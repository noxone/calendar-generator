package org.olafneumann.calendargenerator.browser
val preferredLanguage: String? = getPreferredLanguage()

private fun getPreferredLanguage() : String? {
    val preferredBrowserLanguage = js("navigator.language")
    if (preferredBrowserLanguage is String) {
        return (preferredBrowserLanguage as String)
            .replace(Regex("^(\\w+-)?(\\w+)$"), "$2")
            .uppercase()
    } else {
        return null
    }
}
