import dev.amaro.editor.models.ActionButton
import dev.amaro.editor.models.DeckButton
import dev.amaro.editor.models.MenuButton
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldHave
import io.kotest.matchers.types.instanceOf

class ButtonsTest: FunSpec() {
    init {
        test("Button Types") {
            val button1 : DeckButton = ActionButton()
            val button2 : DeckButton = MenuButton()
            button1 shouldHave instanceOf<ActionButton>()
            button2 shouldHave instanceOf<MenuButton>()
        }
    }
}