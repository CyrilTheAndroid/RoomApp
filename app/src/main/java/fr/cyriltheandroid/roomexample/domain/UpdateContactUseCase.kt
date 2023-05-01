package fr.cyriltheandroid.roomexample.domain

import fr.cyriltheandroid.roomexample.data.model.Contact
import fr.cyriltheandroid.roomexample.data.repository.ContactRepository
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(contact: Contact) = contactRepository.updateContact(contact)
}