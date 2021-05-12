import com.appmattus.kotlinfixture.decorator.constructor.ModestConstructorStrategy
import com.appmattus.kotlinfixture.decorator.constructor.constructorStrategy
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import dev.amaro.editor.models.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import io.kotest.matchers.types.instanceOf

class ButtonsTest : FunSpec() {
    init {
        test("Button Types") {
            val button1: DeckButton = ActionButton(fixture())
            val button2: DeckButton = MenuButton(fixture(), fixture())
            button1 shouldHave instanceOf<ActionButton>()
            button2 shouldHave instanceOf<MenuButton>()
        }

        test("A Menu needs to have two buttons at least") {
            shouldThrow<IllegalArgumentException> {
                DeckMenu(fixture<ActionButton>())
            }
        }

        test("A Menu can have two or more buttons") {
            val menu = DeckMenu(fixture(), fixture())
            menu.buttons.size shouldBe 2
        }

        test("A Menu can have three buttons") {
            val menu = DeckMenu(fixture(), fixture(), fixture())
            menu.buttons.size shouldBe 3
        }

        test("A MenuButton need a menu") {
            val menu = DeckMenu(fixture(), fixture())
            val button = MenuButton(fixture(), menu)
            button.menu.buttons.size shouldBe 2
        }

    }
}

inline fun <reified T> fixture(): T =
    kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        constructorStrategy(ModestConstructorStrategy)
        factory<DeckButton> { ActionButton(fixture()) }
        factory<Deck> { Deck(fixture(), fixture(), 2, 3) }
    }()
