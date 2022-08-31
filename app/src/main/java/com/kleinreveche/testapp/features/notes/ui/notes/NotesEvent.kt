package com.kleinreveche.testapp.features.notes.ui.notes

import com.kleinreveche.testapp.features.notes.domain.model.Note
import com.kleinreveche.testapp.features.notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class OrderNote(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
