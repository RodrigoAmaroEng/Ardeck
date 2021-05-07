package dev.amaro.editor.models

data class DeckMask(
    val horizontalMargin: Short,
    val verticalMargin: Short,
    val horizontalSpace: Short = horizontalMargin,
    val verticalSpace: Short = verticalMargin
) {
    companion object {
        fun fromDeck(deck: Deck, distribution: Distribution = Distribution.Equal): DeckMask {
            val hAvailSpace = deck.width - deck.columns * deck.buttonSize
            val vAvailSpace = deck.height - deck.rows * deck.buttonSize
            var hSpace = (hAvailSpace / (deck.columns + 1)).toShort()
            var vSpace = (vAvailSpace / (deck.rows + 1)).toShort()
            return if (distribution == Distribution.Equal)
                DeckMask(hSpace, vSpace)
            else {
                val space = minOf(hSpace, vSpace)
                hSpace = ((hAvailSpace - (deck.columns - 1) * space) / 2).toShort()
                vSpace = ((vAvailSpace - (deck.rows - 1) * space) / 2).toShort()
                DeckMask(hSpace, vSpace, space, space)
            }
        }
    }

    enum class Distribution {
        Equal,
        Packed
    }
}