package dev.amaro.editor.logic

import dev.amaro.editor.support.Storage
import dev.amaro.sonic.IAction
import dev.amaro.sonic.IMiddleware
import dev.amaro.sonic.IProcessor
import java.nio.file.Path

class FileManagementMiddleware(private val storage: Storage) : IMiddleware<DeckEditorState> {
    override fun process(action: IAction, state: DeckEditorState, processor: IProcessor<DeckEditorState>) {
        if (action is Action.LoadDeck)
            processor.reduce(Action.OpenDeck(storage.load(Path.of(action.filePath))))
        if (action is Action.SaveDeck)
            storage.save(state.deck!!, Path.of(action.filePath))
    }
}