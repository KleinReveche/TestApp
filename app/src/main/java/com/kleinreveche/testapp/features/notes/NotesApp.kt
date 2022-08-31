package com.kleinreveche.testapp.features.notes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kleinreveche.testapp.features.notes.ui.edit_note.AddEditNoteScreen
import com.kleinreveche.testapp.features.notes.ui.notes.NotesScreen
import com.kleinreveche.testapp.features.notes.ui.util.NotesScreen

@Composable
fun NotesApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NotesScreen.NotesNotesScreen.route
    ) {
        composable(route = NotesScreen.NotesNotesScreen.route) {
            NotesScreen(navController = navController)
        }
        composable(
            route = NotesScreen.AddEditNoteNotesScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
    }
}