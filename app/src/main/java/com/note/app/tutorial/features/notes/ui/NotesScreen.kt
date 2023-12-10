package com.note.app.tutorial.features.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@Composable
fun NotesScreen(navController: NavHostController) {
    Box {
        if (exampleNotes.isEmpty())
            NotesEmptyScreen()
        else
            NotesListScreen(exampleNotes, navController)
        FloatingActionButton(
            onClick = { navController.navigate("/AddEditScreen/null") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}

@Composable
fun NotesListScreen(notes: List<Note>, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn {
            items(notes) {
                NoteItem(it, navController)
            }
        }
    }
}

@Composable
private fun NotesEmptyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DoNotDisturb,
                contentDescription = "",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Nie masz jeszcze żadnych notatek.",
                style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center)
            )
            Text(
                text = "Kliknij '+' w prawym dolnym rogu by dodać nową notatke",
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.LightGray
                )
            )

        }

    }
}

@Preview
@Composable
fun NotesScreenPreview() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface {
            NotesScreen(navController = rememberNavController())
        }
    }
}

@Preview
@Composable
fun NotesScreenEmptyPreview() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface {
            NotesEmptyScreen()
        }
    }
}

data class Note(val title: String, val content: String, val tag: NoteTag?, val id: Int = 0)

data class NoteTag(val name: String, val color: Color)

val exampleNotes = listOf(
    Note(
        "Piewsza super notatka",
        LoremIpsum(8).values.joinToString(),
        NoteTag("Praca", Color.Blue)
    ),
    Note(
        "Druga krótka",
        LoremIpsum(1).values.joinToString(),
        NoteTag("Zabawa", Color.Red)
    ),
    Note(
        "Trzecia super duper długa notatka o tym jak chrystus jezus zbawił świat",
        LoremIpsum(100).values.joinToString(),
        NoteTag("Nauka", Color.Green)
    ),
    Note(
        "Czwarta super notatka bez tagu",
        LoremIpsum(20).values.joinToString(),
        null
    )
)
