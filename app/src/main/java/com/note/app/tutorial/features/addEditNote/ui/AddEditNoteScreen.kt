package com.note.app.tutorial.features.addEditNote.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.note.app.tutorial.features.notes.ui.NoteTag
import com.note.app.tutorial.ui.theme.NoteAppTutorialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(noteId: Int?, navController: NavHostController) {
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
            TagToggleButtons(NoteTag("none", Color.Transparent), onTagSelected = {})
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { }) {
                Text(text = "Zapisz")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TagToggleButtons(selectedTag: NoteTag, onTagSelected: (NoteTag) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        FilterChip(
            onClick = {
                onTagSelected(NoteTag("Praca", Color.Blue.copy(0.3f)))
            },
            label = {
                Text("Praca")
            },
            selected = selectedTag.name == "Praca",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Blue.copy(0.3f)
            )
        )
        FilterChip(
            onClick = {
                onTagSelected(NoteTag("Zabawa", Color.Red.copy(0.3f)))
            },
            label = {
                Text("Zabawa")
            },
            selected = selectedTag.name == "Zabawa",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Red.copy(0.3f)
            )
        )
        FilterChip(
            onClick = {
                onTagSelected(NoteTag("Nauka", Color.Green.copy(0.3f)))
            },
            label = {
                Text("Nauka")
            },
            selected = selectedTag.name == "Nauka",
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Green.copy(0.3f)
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