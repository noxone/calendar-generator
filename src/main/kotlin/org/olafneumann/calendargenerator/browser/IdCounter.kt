package org.olafneumann.calendargenerator.browser

object IdCounter {
    private var count = 0
    fun nextId(): String = "unique-id-${count++}"
}
