package dev.amaro.editor.models

import java.lang.IllegalArgumentException

class DeckMenu(val buttons: List<DeckButton>) {
    constructor(vararg buttonList: DeckButton) : this(buttonList.toList())

    init {
        if (buttons.size < 2) throw IllegalArgumentException()
    }
}
