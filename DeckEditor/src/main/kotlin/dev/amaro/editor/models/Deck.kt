package dev.amaro.editor.models

import kotlin.math.min

data class Deck(
    val width: Short,
    val height: Short,
    val rows: Short,
    val columns: Short,
    val buttons: List<DeckButton?> = IntRange(1, rows * columns).map { null }
) {
    init {
        if (buttons.size != rows * columns) throw IllegalArgumentException()
    }

    val buttonSize = (min(width / columns.toDouble(), height / rows.toDouble()) * 0.9).toInt()
}