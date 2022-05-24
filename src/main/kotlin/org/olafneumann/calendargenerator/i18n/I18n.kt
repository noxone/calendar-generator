package org.olafneumann.calendargenerator.i18n

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.w3c.dom.Navigator
import org.w3c.dom.Window
import org.w3c.fetch.Request
import kotlin.js.Promise

object I18n {
    private val recognizerMap = mutableMapOf<Regex, String>()
    private val replacementMap = mutableMapOf<String, String>()

    init {
        recognizerMap[Regex("January")] = "Januar"
        recognizerMap[Regex("May")] = "Mai"
        recognizerMap[Regex("Calendar")] = "XKalender"
        recognizerMap[Regex("Calendar (\\d+)")] = "$1 Kalender"

        Request("texts_de.json").text()
            .then {
                // TODO: actually implement
                // console.log(it)
                val json = Json.parseToJsonElement(it) as JsonObject
                val entries = json["entries"] as JsonObject
                val map = entries.map { entry -> Regex(entry.key) to entry.value.jsonPrimitive.content }.toMap()
                recognizerMap.clear()
                recognizerMap.putAll(map)
                replacementMap.clear()
            }
            .catch {
                // console.error(it)
            }
    }

    fun translate(input: String) = replacementMap.getOrPut(input) { createActualReplacement(input) }

    private fun createActualReplacement(input: String): String {
        val translationResult = recognizerMap.entries
            .firstOrNull { it.key.matches(input) }
            ?.let { input.replace(it.key, it.value) }
        if (translationResult == null) {
            // TODO: build this!
            // console.warn("No translation available for", input)
        }
        return translationResult ?: input
    }
}

fun String.translate() = I18n.translate(this)
