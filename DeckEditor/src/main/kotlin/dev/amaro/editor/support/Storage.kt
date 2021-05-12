package dev.amaro.editor.support

import dev.amaro.editor.models.Deck
import java.nio.file.Path

interface Storage {
    fun save(deck: Deck, path: Path)
    fun load(path: Path): Deck
}

class FileStorage(private val serializer: Serializer) : Storage {
    override fun save(deck: Deck, path: Path) {
        path.toFile().writeText(serializer.serialize(deck))
    }

    override fun load(path: Path): Deck {
        return serializer.deserialize(path.toFile().readText())
    }
}