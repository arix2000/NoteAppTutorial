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
import androidx.compose.runtime.LaunchedEffect
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
import com.note.app.tutorial.core.database.entities.Note
import com.note.app.tutorial.core.database.entities.NoteTag
import com.note.app.tutorial.features.notes.NoteEvent
import com.note.app.tutorial.features.notes.NoteViewModel
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme
import org.koin.compose.koinInject

@Composable
fun NotesScreen(navController: NavHostController, viewModel: NoteViewModel = koinInject()) {
    LaunchedEffect(key1 = true) {
        viewModel.invokeEvent(NoteEvent.GetAllNotesEvent)
    }
    NotesScreenContent(viewModel.state.value.notes, { viewModel.invokeEvent(it) }, navController)
}

@Composable
private fun NotesScreenContent(
    notes: List<Note>,
    invokeEvent: (NoteEvent) -> Unit,
    navController: NavHostController
) {
    Box {
        if (notes.isEmpty())
            NotesEmptyScreen()
        else
            NotesListScreen(notes, navController, invokeEvent)
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
fun NotesListScreen(
    notes: List<Note>,
    navController: NavController,
    invokeEvent: (NoteEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn {
            items(notes) {
                NoteItem(it, navController, invokeEvent)
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
    val exampleNotes = listOf(
        Note(
            0,
            "Piewsza super notatka",
            LoremIpsum(8).values.joinToString(),
            NoteTag("Praca", Color.Blue)
        ),
        Note(
            1,
            "Druga krótka",
            LoremIpsum(1).values.joinToString(),
            NoteTag("Zabawa", Color.Red)
        ),
        Note(
            2,
            "Trzecia super duper długa notatka o tym jak chrystus jezus zbawił świat",
            LoremIpsum(100).values.joinToString(),
            NoteTag("Nauka", Color.Green)
        ),
        Note(
            3,
            "Czwarta super notatka bez tagu",
            LoremIpsum(20).values.joinToString(),
            NoteTag("none", Color.Transparent)
        )
    )
    NoteAppTutorialTheme(darkTheme = true) {
        Surface {
            NotesScreenContent(exampleNotes, {  }, rememberNavController())
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
