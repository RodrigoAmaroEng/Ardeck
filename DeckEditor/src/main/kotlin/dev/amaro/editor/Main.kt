package dev.amaro.editor

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Menu
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuItem
import dev.amaro.editor.logic.DeckStateManager
import dev.amaro.editor.models.Deck
import dev.amaro.editor.models.DeckButton
import dev.amaro.editor.models.DeckMask
import org.koin.java.KoinJavaComponent.inject

val deck = Deck(320, 240, 2, 3)
val mask = DeckMask.fromDeck(deck, DeckMask.Distribution.Equal)


fun main() {
    val stateManager: DeckStateManager by inject(DeckStateManager::class.java)
    val state = mutableStateOf(stateManager.listen())
    val itemEditor = MenuItem("Editor", onClick = { })
    val itemSimulator = MenuItem("Simulator")
    val menu = Menu("Mode", itemEditor, itemSimulator)
    val menuBar = MenuBar(menu)
    Window(title = "Ardeck", size = IntSize(deck.width.toInt(), deck.height + 28), menuBar = menuBar) {
        var count = 0
        MaterialTheme {
            Column(
                Modifier.padding(mask.horizontalMargin.dp, mask.verticalMargin.dp)
                    .width(deck.width.dp)
                    .height(deck.height.dp)
            ) {
                repeat(deck.rows.toInt()) { row ->
                    Row(Modifier.fillMaxWidth()) {
                        repeat(deck.columns.toInt()) { col ->
                            Column {
                                renderButton(count, deck.buttons[count], deck.buttonSize.dp)
                                count++
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
}

@Composable
private fun renderButton(number: Int, deckButton: DeckButton?, buttonSize: Dp) {
    Button(modifier = Modifier.size(buttonSize), onClick = { }) {
        val name = deckButton?.name ?: "Press to +"
        Column {
            Text("$number", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(Modifier.height(4.dp))
            Text(name, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

val Short.dp
    get() = this.toInt().dp

@Composable
fun setUpButton() {
    AlertDialog(onDismissRequest = {},
        title = { Text("Hello title") },
        confirmButton = {
            Button(onClick = {
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = {
            }) {
                Text("Dismiss")
            }
        },
        text = { Text("Hello text") })
}