package com.kleinreveche.testapp.features.notes.ui.util

sealed class NotesScreen(val route: String) {
    object NotesNotesScreen: NotesScreen("notes_screen")
    object AddEditNoteNotesScreen: NotesScreen("add_edit_note_screen")
}