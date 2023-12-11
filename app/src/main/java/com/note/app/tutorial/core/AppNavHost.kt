package com.note.app.tutorial.core

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.note.app.tutorial.features.notes.ui.AddEditNoteScreen
import com.note.app.tutorial.features.notes.ui.NotesScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "/HomeScreen",
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {
        composable("/HomeScreen") {
            NotesScreen(navController)
        }
        composable("/AddEditScreen/{NoteId}") { backStackEntry ->
            backStackEntry.arguments?.getString("NoteId")?.let {
                AddEditNoteScreen(it.toIntOrNull(), navController)
            }
        }
    }
}