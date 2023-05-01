package fr.cyriltheandroid.roomexample.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cyriltheandroid.roomexample.data.model.Contact
import fr.cyriltheandroid.roomexample.domain.AddNewContactUseCase
import fr.cyriltheandroid.roomexample.domain.DeleteContactUseCase
import fr.cyriltheandroid.roomexample.domain.GetContactsUseCase
import fr.cyriltheandroid.roomexample.domain.UpdateContactUseCase
import fr.cyriltheandroid.roomexample.ui.model.ContactUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    val getContactsUseCase: GetContactsUseCase,
    val addNewContactUseCase: AddNewContactUseCase,
    val updateContactUseCase: UpdateContactUseCase,
    val deleteContactUseCase: DeleteContactUseCase
): ViewModel() {

    private val contacts = MutableStateFlow(emptyList<Contact>())

    private val _contactsUi = MutableStateFlow(emptyList<ContactUi>())
    val contactsUi = _contactsUi.asStateFlow()

    init {
        viewModelScope.launch {
            getContactsUseCase().collect {
                contacts.value = it

                _contactsUi.value = it.map {contact ->
                    ContactUi(
                        contact.firstName,
                        contact.lastName,
                        contact.phoneNumber
                    )
                }
            }
        }
    }

    fun addNewContact(firstName: String, lastName: String, phoneNumber: String) {
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber
        )
        viewModelScope.launch(Dispatchers.IO) {
            addNewContactUseCase(contact)
        }
    }

    fun updateContact(index: Int, firstName: String, lastName: String, phoneNumber: String) {
        val idContact = contacts.value[index].id
        val updatedContact = Contact(
            id = idContact,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber
        )

        viewModelScope.launch(Dispatchers.IO) {
            updateContactUseCase(updatedContact)
        }
    }

    fun deleteContact(index: Int) {
        val idContact = contacts.value[index].id
        viewModelScope.launch(Dispatchers.IO) {
            deleteContactUseCase(idContact)
        }
    }
}