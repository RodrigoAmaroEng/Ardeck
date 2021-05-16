package dev.amaro.editor.logic

import dev.amaro.editor.models.Deck
import dev.amaro.editor.models.DeckButton
import dev.amaro.sonic.IAction

sealed class Action : IAction {
    data class LoadDeck(val filePath: String) : Action()
    data class SaveDeck(val filePath: String) : Action()
    data class OpenDeck(val deck: Deck) : Action()
    data class EditButton(val index: Int) : Action()
    data class SetButton(val index: Int, val button: DeckButton) : Action()
}