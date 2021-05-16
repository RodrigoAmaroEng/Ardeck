package dev.amaro.editor.logic

import dev.amaro.sonic.IAction
import dev.amaro.sonic.IMiddleware
import dev.amaro.sonic.IProcessor

class OperationsMiddleware : IMiddleware<DeckEditorState> {
    override fun process(action: IAction, state: DeckEditorState, processor: IProcessor<DeckEditorState>) {
        if (action is Action.SetButton) {
            processor.reduce(action)
        } else if (action is Action.EditButton) {
            processor.reduce(action)
        }
    }
}