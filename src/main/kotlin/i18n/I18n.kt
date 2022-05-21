package i18n

object I18n {
    private val map: Map<Regex, String>

    init {
        val map = mutableMapOf<Regex, String>()
        map[Regex("January")] = "Januar"
        map[Regex("May")] = "Mai"
        map[Regex("Calendar")] = "XKalender"
        map[Regex("Calendar (\\d+)")] = "$1 Kalender"
        this.map = map
    }

    fun translate(input: String): String {
        return map.entries
            .firstOrNull { it.key.matches(input) }
            ?.let { input.replace(it.key, it.value) }
            ?: input
    }
}

fun String.translate() = I18n.translate(this)
