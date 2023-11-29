package com.irmak.cleanarcremoteanddb.domain.repository

import android.provider.ContactsContract
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<ContactsContract.CommonDataKinds.Note>>

    suspend fun getNoteById(id: Int): ContactsContract.CommonDataKinds.Note?

    suspend fun insertNote(note: ContactsContract.CommonDataKinds.Note)

    suspend fun deleteNote(note: ContactsContract.CommonDataKinds.Note)
}
