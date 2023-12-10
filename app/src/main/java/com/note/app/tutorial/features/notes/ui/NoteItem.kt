package com.note.app.tutorial.features.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Note, navController: NavController) {
    Card(
        onClick = {
            navController.navigate("/AddEditScreen/" + note.id)
        },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2B2B2B)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
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
            if (note.tag != null) {
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
}

@Preview
@Composable
fun NoteItemPrev() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface {
            Column {
                exampleNotes.forEach {
                    NoteItem(note = it, rememberNavController())
                }
            }
        }
    }
}