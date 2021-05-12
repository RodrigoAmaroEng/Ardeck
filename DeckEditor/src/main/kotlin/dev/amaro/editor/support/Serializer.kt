package dev.amaro.editor.support

import com.google.gson.Gson
import dev.amaro.editor.models.Deck

interface Serializer {
    fun serialize(deck: Deck): String
    fun deserialize(payload: String): Deck
}

class GsonSerializer : Serializer {
    private val serializer = Gson()
    override fun serialize(deck: Deck): String {
        return serializer.toJson(deck)
    }

    override fun deserialize(payload: String): Deck {
        return serializer.fromJson(payload, Deck::class.java)
    }

}