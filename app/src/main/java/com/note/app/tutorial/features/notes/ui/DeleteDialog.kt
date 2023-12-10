package com.note.app.tutorial.features.notes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.DeleteForever, contentDescription = "")
        },
        title = {
            Text(text = "Czy napewno checsz usunąć tą notatke?")
        },
        text = {
            Text(text = "Usunięcie notatki nie może być cofnięte, jesteś pewny że chcesz to zrobić? Dane zostaną utracone na zawsze.")
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Usuń notatke", style = TextStyle(color = Color.Red))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Anuluj")
            }
        }
    )
}

@Preview
@Composable
fun DeleteDialogPrev() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box {
                DeleteDialog({}) {}
            }
        }
    }
}