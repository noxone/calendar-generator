package org.olafneumann.calendargenerator.browser

val navigatorLanguage: String? = js("navigator.language") as String?

val browserLanguage: String = navigatorLanguage
    ?.replace(Regex("^(\\w+)-?(\\w+)?$"), "$1") ?: "en"

val userRegion: String? = navigatorLanguage
    ?.replace(Regex("^(\\w+-)?(\\w+)$"), "$2")
    ?.uppercase()
