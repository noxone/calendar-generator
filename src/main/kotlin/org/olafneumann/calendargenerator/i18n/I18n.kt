package org.olafneumann.calendargenerator.i18n

object I18n {
    private val recognizerMap: Map<Regex, String>
    private val replacementMap = mutableMapOf<String, String>()

    init {
        val map = mutableMapOf<Regex, String>()
        map[Regex("January")] = "Januar"
        map[Regex("May")] = "Mai"
        map[Regex("Calendar")] = "XKalender"
        map[Regex("Calendar (\\d+)")] = "$1 Kalender"
        recognizerMap = map
    }

    fun translate(input: String) = replacementMap.getOrPut(input) { createActualReplacement(input) }

    private fun createActualReplacement(input: String): String {
        val translationResult = recognizerMap.entries
            .firstOrNull { it.key.matches(input) }
            ?.let { input.replace(it.key, it.value) }
        if (translationResult == null) {
            console.warn("No translation available for", input)
        }
        return translationResult ?: input
    }
}

fun String.translate() = I18n.translate(this)
