package dev.amaro.editor

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.amaro.editor.models.Deck
import dev.amaro.editor.models.DeckMask

val deck = Deck(320, 240, 2, 3)
val mask = DeckMask.fromDeck(deck, DeckMask.Distribution.Equal)

fun main() = Window(title = "Ardeck", size = IntSize(deck.width.toInt(), deck.height + 28)) {
    var count = 0

    MaterialTheme {
        Column(
            Modifier.padding(mask.horizontalMargin.dp, mask.verticalMargin.dp).width(deck.width.dp)
                .height(deck.height.dp)
        ) {
            repeat(deck.rows.toInt()) { row ->
                Row(Modifier.fillMaxWidth()) {
                    repeat(deck.columns.toInt()) { col ->
                        Column {
                            Button(modifier = Modifier.size(deck.buttonSize.dp), onClick = {}) {
                                val name = deck.buttons[count]?.name ?: "Press to +"
                                Column {
                                    Text("${count++}", modifier = Modifier.align(Alignment.CenterHorizontally))
                                    Spacer(Modifier.height(4.dp))
                                    Text(name, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                                }
                            }
                            if (row < deck.rows - 1) {
                                Spacer(Modifier.width(0.dp).height(mask.verticalSpace.dp))
                            }
                        }
                        if (col < deck.columns - 1) {
                            Spacer(Modifier.height(0.dp).width(mask.horizontalSpace.dp))
                        }
                    }
                }
            }
        }
    }
}

val Short.dp
    get() = this.toInt().dp