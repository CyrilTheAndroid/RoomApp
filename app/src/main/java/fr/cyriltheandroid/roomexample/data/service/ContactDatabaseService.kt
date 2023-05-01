package fr.cyriltheandroid.roomexample.data.service

import fr.cyriltheandroid.roomexample.data.room.ContactEntity
import kotlinx.coroutines.flow.Flow

interface ContactDatabaseService {
    fun addNewContact(contactEntity: ContactEntity)
    fun getContacts(): Flow<List<ContactEntity>>
    fun updateContact(contactEntity: ContactEntity)
    fun deleteContact(idContact: Long)
}