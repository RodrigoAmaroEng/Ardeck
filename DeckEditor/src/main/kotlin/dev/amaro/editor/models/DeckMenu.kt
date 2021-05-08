package dev.amaro.editor.models

import java.lang.IllegalArgumentException

class DeckMenu(vararg buttonList: DeckButton) {
    init {
        if (buttonList.size < 2) throw IllegalArgumentException()
    }
    val buttons = buttonList.toList()
}
