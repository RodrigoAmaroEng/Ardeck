import dev.amaro.editor.models.Deck
import dev.amaro.editor.models.DeckMask
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class DeckTest : FunSpec() {
    init {
        test("Deck instance") {
            val deck = Deck(320, 240, 2, 3)
            deck.width shouldBe 320
            deck.height shouldBe 240
            deck.rows shouldBe 2
            deck.columns shouldBe 3
        }

        test("Ideal button size for squared ratio") {
            val deck = Deck(320, 240, 2, 3)
            deck.buttonSize shouldBe 96
        }

        test("Get margin settings for distributed mode") {
            val mask = DeckMask.fromDeck(Deck(320, 240, 2, 3))
            mask shouldBe DeckMask(8, 16)
        }

        test("Get margin settings for packed mode") {
            val mask = DeckMask.fromDeck(Deck(320, 240, 2, 3), DeckMask.Distribution.Packed)
            mask shouldBe DeckMask(8, 20, 8, 8)
        }

        test("Deck has slots for the amount of available buttons") {
            val deck = Deck(320, 240, 2, 3)
            deck.buttons.size shouldBe 6
        }

        test("Deck must receive the exact number of buttons") {
            shouldThrow<IllegalArgumentException> {
                Deck(320, 240, 2, 3, IntRange(1, 5).map { null })
            }
        }
    }
}



