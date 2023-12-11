package com.note.app.tutorial.features.notes.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.note.app.tutorial.core.database.entities.Note
import com.note.app.tutorial.core.database.entities.NoteTag
import com.note.app.tutorial.features.notes.NoteEvent
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(note: Note, navController: NavController, invokeEvent: (NoteEvent) -> Unit) {
    var isDeleteDialogShowed by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2B2B2B)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .combinedClickable(onClick = {
                navController.navigate("/AddEditScreen/" + note.id)
            }, onLongClick = {
                isDeleteDialogShowed = true
            })
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = note.title,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.content,
                style = TextStyle(fontSize = 18.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (note.tag != NoteTag("none", Color.Transparent)) {
                Box(
                    Modifier
                        .background(note.tag.color.copy(alpha = 0.3f), shape = CircleShape)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                        .align(Alignment.End)
                ) {
                    Text(text = note.tag.name)
                }
            }
        }
    }
    if (isDeleteDialogShowed)
        DeleteDialog(
            onDismiss = { isDeleteDialogShowed = false },
            onConfirmation = { invokeEvent(NoteEvent.DeleteNoteEvent(note)) })
}

@Preview
@Composable
fun NoteItemPrev() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface {
            Column {
                NoteItem(
                    note = Note(0, "STRING", "STRING content", NoteTag("Praca", Color.Blue)),
                    navController = rememberNavController(),
                    invokeEvent = { }
                )
            }
        }
    }
}