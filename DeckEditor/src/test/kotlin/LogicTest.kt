import dev.amaro.editor.logic.Action
import dev.amaro.editor.logic.DeckEditorState
import dev.amaro.editor.logic.DeckStateManager
import dev.amaro.editor.models.ActionButton
import dev.amaro.editor.models.Deck
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LogicTest : FunSpec() {
    init {
        test("Initial state does not have a deck") {
            val state = DeckEditorState()
            state.deck shouldBe null
        }

        test("Open deck command set the current deck") {
            val stateManager = DeckStateManager()
            val deck: Deck = fixture()
            stateManager.process(Action.OpenDeck(deck))
            stateManager.listen().value.deck shouldBe deck
        }

        test("Set action for button command") {
            val button: ActionButton = fixture()
            val deck: Deck = fixture()
            val stateManager = DeckStateManager(DeckEditorState(deck = deck))
            stateManager.process(Action.SetButton(1, button))
            stateManager.listen().value.deck shouldBe deck.copy(
                buttons = deck.buttons.toMutableList().apply { set(1, button) })
        }
    }
}