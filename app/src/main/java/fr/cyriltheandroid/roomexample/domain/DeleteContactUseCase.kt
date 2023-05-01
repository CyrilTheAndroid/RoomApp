package fr.cyriltheandroid.roomexample.domain

import fr.cyriltheandroid.roomexample.data.repository.ContactRepository
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(idContact: Long) = contactRepository.deleteContact(idContact)
}