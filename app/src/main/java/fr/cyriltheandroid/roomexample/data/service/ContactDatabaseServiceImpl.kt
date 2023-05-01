package fr.cyriltheandroid.roomexample.data.service

import fr.cyriltheandroid.roomexample.data.room.ContactDao
import fr.cyriltheandroid.roomexample.data.room.ContactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactDatabaseServiceImpl @Inject constructor(
    private val contactDao: ContactDao
): ContactDatabaseService {
    override fun addNewContact(contactEntity: ContactEntity) = contactDao.addNewContact(contactEntity)

    override fun getContacts(): Flow<List<ContactEntity>> = contactDao.getContacts()

    override fun updateContact(contactEntity: ContactEntity) = contactDao.updateContact(contactEntity)

    override fun deleteContact(idContact: Long) = contactDao.deleteContact(idContact)
}