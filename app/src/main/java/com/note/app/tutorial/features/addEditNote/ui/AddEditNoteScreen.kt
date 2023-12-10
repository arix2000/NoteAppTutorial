package com.note.app.tutorial.features.addEditNote.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.note.app.tutorial.features.notes.ui.Note
import com.note.app.tutorial.features.notes.ui.exampleNotes
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(noteId: Int?, navController: NavHostController) {
    val note = exampleNotes[noteId ?: 1]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Button(
            onClick = { navController.popBackStack() },
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Tytuł") },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "napisz coś...") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.weight(1f)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            TagToggleButtons(note)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { }) {
                Text(text = "Zapisz")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TagToggleButtons(note: Note?) {
    var selected: String? by remember { mutableStateOf(null) }
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        FilterChip(
            onClick = {
                selected = if (selected == "Praca") null else "Praca"
            },
            label = {
                Text("Praca")
            },
            selected = selected == "Praca",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Blue.copy(0.3f)
            )
        )
        FilterChip(
            onClick = {
                selected = if (selected == "Zabawa") null else "Zabawa"
            },
            label = {
                Text("Zabawa")
            },
            selected = selected == "Zabawa",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Red.copy(0.3f)
            )
        )
        FilterChip(
            onClick = {
                selected = if (selected == "Nauka") null else "Nauka"
            },
            label = {
                Text("Nauka")
            },
            selected = selected == "Nauka",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Green.copy(
                    0.3f
                )
            )
        )
    }

}

@Preview
@Composable
fun AddEditNoteScreenPrev() {
    NoteAppTutorialTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            AddEditNoteScreen(noteId = 2, navController = rememberNavController())
        }
    }
}