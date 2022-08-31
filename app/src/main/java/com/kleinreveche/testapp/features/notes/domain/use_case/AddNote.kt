package com.kleinreveche.testapp.features.notes.domain.use_case

import com.kleinreveche.testapp.features.notes.domain.model.InvalidNoteException
import com.kleinreveche.testapp.features.notes.domain.model.Note
import com.kleinreveche.testapp.features.notes.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote (
    private val repository: NoteRepository
    ) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }

}