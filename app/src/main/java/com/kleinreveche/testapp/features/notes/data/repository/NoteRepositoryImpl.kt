package com.kleinreveche.testapp.features.notes.data.repository

import com.kleinreveche.testapp.features.notes.data.data_source.NoteDao
import com.kleinreveche.testapp.features.notes.domain.model.Note
import com.kleinreveche.testapp.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

}