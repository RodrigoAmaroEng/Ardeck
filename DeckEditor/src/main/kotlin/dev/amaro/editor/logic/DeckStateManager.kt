package dev.amaro.editor.logic

import dev.amaro.sonic.IAction
import dev.amaro.sonic.IProcessor
import dev.amaro.sonic.Processor
import dev.amaro.sonic.StateManager

class DeckStateManager(initialState: DeckEditorState = DeckEditorState()) :
    StateManager<DeckEditorState>(initialState) {
    override val processor: IProcessor<DeckEditorState> = object : Processor<DeckEditorState>(this) {

        override fun reduce(action: IAction) {
            if (action is Action.OpenDeck) {
                state.value = state.value.copy(deck = action.deck)
            } else if (action is Action.SetButton) {
                val deck = state.value.deck?.let {
                    it.copy(buttons = it.buttons.toMutableList().apply { set(action.index, action.button) })
                }
                state.value = state.value.copy(deck = deck)
            }
        }

    }
}