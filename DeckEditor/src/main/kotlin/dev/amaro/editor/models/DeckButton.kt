package dev.amaro.editor.models

interface DeckButton {
    val name: String
}

data class ActionButton(override val name: String) : DeckButton
data class MenuButton(override val name: String, val menu: DeckMenu) : DeckButton
