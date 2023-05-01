package fr.cyriltheandroid.roomexample.data.repository

import fr.cyriltheandroid.roomexample.data.model.Contact
import fr.cyriltheandroid.roomexample.data.room.ContactEntity
import fr.cyriltheandroid.roomexample.data.service.ContactDatabaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDatabaseService: ContactDatabaseService
) : ContactRepository {
    override fun addNewContact(contact: Contact) {
        val contactEntity = ContactEntity(
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
        )
        contactDatabaseService.addNewContact(contactEntity)
    }

    override fun getContacts(): Flow<List<Contact>> =
        contactDatabaseService.getContacts().map { entities ->
            entities.map {
                Contact(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    phoneNumber = it.phoneNumber
                )
            }
        }.flowOn(Dispatchers.IO)

    override fun updateContact(contact: Contact) {
        val contactEntity = ContactEntity(
            id = contact.id,
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber
        )

        contactDatabaseService.updateContact(contactEntity)
    }

    override fun deleteContact(idContact: Long) {
        contactDatabaseService.deleteContact(idContact)
    }
}