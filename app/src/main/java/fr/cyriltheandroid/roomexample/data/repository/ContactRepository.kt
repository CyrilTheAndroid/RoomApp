package fr.cyriltheandroid.roomexample.data.repository

import fr.cyriltheandroid.roomexample.data.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun addNewContact(contact: Contact)
    fun getContacts(): Flow<List<Contact>>
    fun updateContact(contact: Contact)
    fun deleteContact(idContact: Long)
}