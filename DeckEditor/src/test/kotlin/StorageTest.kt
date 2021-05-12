import dev.amaro.editor.logic.DeckEditorState
import dev.amaro.editor.models.Deck
import dev.amaro.editor.support.FileStorage
import dev.amaro.editor.support.GsonSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import java.io.File

class StorageTest : FunSpec() {
    companion object {
        const val SAMPLE_JSON =
            "{\"width\":320,\"height\":240,\"rows\":2,\"columns\":3,\"buttons\":[null,null,null,null,null,null],\"buttonSize\":96}"
    }

    init {
        test("Serializer") {
            val serializer = GsonSerializer()
            val deck = Deck(320, 240, 2, 3)
            serializer.serialize(deck) shouldBe SAMPLE_JSON
        }

        test("Deserializer") {
            val serializer = GsonSerializer()
            val deck = Deck(320, 240, 2, 3)
            serializer.deserialize(SAMPLE_JSON) shouldBe deck
        }

        test("Store file") {
            val root = File(this::class.java.classLoader.getResource(".")!!.path)
            root.listFiles()?.forEach { it.delete() }
            val storage = FileStorage(GsonSerializer())
            val deck = Deck(320, 240, 2, 3)
            storage.save(deck, File(root.path, "test.deck").toPath())
            (root.listFiles() ?: arrayOf<File>()) shouldContain File(root, "test.deck")
            File(root, "test.deck").readText() shouldBe SAMPLE_JSON
        }

        test("Load file") {
            val sample = File(this::class.java.classLoader.getResource("sample.deck")!!.path).toPath()
            val storage = FileStorage(GsonSerializer())
            val deck = Deck(320, 240, 2, 3)
            val loadedDeck = storage.load(sample)
            loadedDeck shouldBe deck
        }
    }
}