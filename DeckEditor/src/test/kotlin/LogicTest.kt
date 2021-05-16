import dev.amaro.editor.logic.*
import dev.amaro.editor.models.ActionButton
import dev.amaro.editor.models.Deck
import dev.amaro.editor.support.Storage
import dev.amaro.sonic.IProcessor
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.nio.file.Path

class LogicTest : FunSpec() {
    init {

        test("Initial state does not have a deck") {
            val state = DeckEditorState()
            state.deck shouldBe null
        }

        test("Open deck command set the current deck") {
            val storage: Storage = mockk(relaxed = true)
            val deck: Deck = fixture()
            every { storage.load(any()) } returns deck
            val stateManager = DeckStateManager(middlewares = arrayOf(OperationsMiddleware(), FileManagementMiddleware(storage)))
            stateManager.process(Action.LoadDeck(fixture()))
            stateManager.listen().value.deck shouldBe deck
        }

        test("Set action for button command") {
            val button: ActionButton = fixture()
            val deck: Deck = fixture()
            val stateManager = DeckStateManager(DeckEditorState(deck = deck), middlewares = arrayOf(OperationsMiddleware()))
            stateManager.process(Action.SetButton(1, button))
            stateManager.listen().value.deck shouldBe deck.copy(
                buttons = deck.buttons.toMutableList().apply { set(1, button) })
        }

        test("File management middleware handle the load deck action") {
            val storage: Storage = mockk(relaxed = true)
            val deck: Deck = fixture()
            every { storage.load(any()) } returns deck
            val middleware = FileManagementMiddleware(storage)
            val processor: IProcessor<DeckEditorState> = mockk(relaxed = true)
            middleware.process(Action.LoadDeck("test.deck"), DeckEditorState(), processor)
            verify { processor.reduce(Action.OpenDeck(deck)) }
        }

        test("File management middleware handle the save deck action") {
            val storage: Storage = mockk(relaxed = true)
            val deck: Deck = fixture()
            val middleware = FileManagementMiddleware(storage)
            val processor: IProcessor<DeckEditorState> = mockk(relaxed = true)
            middleware.process(Action.SaveDeck("test.deck"), DeckEditorState(deck = deck), processor)
            verify { storage.save(deck, Path.of("test.deck")) }
        }

        test("Edit action is dispatched") {
            val middleware = OperationsMiddleware()
            val processor: IProcessor<DeckEditorState> = mockk(relaxed = true)
            middleware.process(Action.EditButton(1), DeckEditorState(deck = fixture()), processor)
            verify { processor.reduce(Action.EditButton(1)) }
        }
    }
}