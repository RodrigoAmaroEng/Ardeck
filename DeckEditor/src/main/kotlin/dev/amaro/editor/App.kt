package dev.amaro.editor

import dev.amaro.editor.logic.DeckEditorState
import dev.amaro.editor.logic.DeckStateManager
import dev.amaro.editor.logic.FileManagementMiddleware
import dev.amaro.editor.support.FileStorage
import dev.amaro.editor.support.GsonSerializer
import dev.amaro.editor.support.Serializer
import dev.amaro.editor.support.Storage
import dev.amaro.sonic.IMiddleware
import org.koin.core.context.startKoin
import org.koin.dsl.module

object App {
    init {
        startKoin {
            module {
                single<Serializer> { GsonSerializer() }
                single<Storage> { FileStorage(get()) }
                single { FileManagementMiddleware(get()) }
                single<Array<IMiddleware<DeckEditorState>>> { arrayOf(get<FileManagementMiddleware>()) }
                single { DeckStateManager(middlewares = get()) }
            }
        }
    }
}