package dev.amaro.editor.models

import kotlin.math.min

data class Deck(
    val width: Short,
    val height: Short,
    val rows: Short,
    val columns: Short
) {
    val buttonSize = (min(width / columns.toDouble(), height / rows.toDouble()) * 0.9).toInt()
}