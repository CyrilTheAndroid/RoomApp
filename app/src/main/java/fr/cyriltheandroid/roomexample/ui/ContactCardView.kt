package fr.cyriltheandroid.roomexample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.cyriltheandroid.roomexample.R
import fr.cyriltheandroid.roomexample.ui.model.ContactUi

@Composable
fun ContactCardView(
    contactUi: ContactUi,
    onUpdateContactClick: (String, String, String) -> Unit,
    onDeleteButtonClick: () -> Unit
) {
    var isUpdating by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        if (isUpdating) {
            ContactInfoUpdatingView(
                firstName = contactUi.firstName,
                lastName = contactUi.lastName,
                phoneNumber = contactUi.phoneNumber,
                onUpdateContactClick = { firstName, lastName, phoneNumber ->
                    onUpdateContactClick(firstName, lastName, phoneNumber)
                    isUpdating = false
                }
            )
        } else {
            ContactInfoNormalView(
                name = "${contactUi.firstName} ${contactUi.lastName}",
                phoneNumber = contactUi.phoneNumber,
                onUpdateButtonClick = { isUpdating = true },
                onDeleteButtonClick = onDeleteButtonClick
            )
        }
    }
}

@Composable
fun ContactInfoNormalView(
    name: String,
    phoneNumber: String,
    onUpdateButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                text = name
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                text = phoneNumber
            )
        }
        Row {
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = onUpdateButtonClick
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_edit),
                    contentDescription = stringResource(id = R.string.icon_edit_content_description)
                )
            }
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = onDeleteButtonClick
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = stringResource(id = R.string.icon_delete_content_description)
                )
            }
        }
    }
}

@Composable
fun ContactInfoUpdatingView(
    firstName: String,
    lastName: String,
    phoneNumber: String,
    onUpdateContactClick: (String, String, String) -> Unit
) {
    var firstNameValue by rememberSaveable { mutableStateOf(firstName) }
    var lastNameValue by rememberSaveable { mutableStateOf(lastName) }
    var phoneNumberValue by rememberSaveable { mutableStateOf(phoneNumber) }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstNameValue,
            onValueChange = {
                firstNameValue = it
            })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastNameValue,
            onValueChange = {
                lastNameValue = it
            })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberValue,
            onValueChange = {
                phoneNumberValue = it
            })
        Button(onClick = {
            onUpdateContactClick(firstNameValue, lastNameValue, phoneNumberValue)
        }) {
            Text(
                color = Color.White,
                text = stringResource(id = R.string.update)
            )
        }
    }
}