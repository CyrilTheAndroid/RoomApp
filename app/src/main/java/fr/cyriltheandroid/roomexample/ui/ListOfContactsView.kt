package fr.cyriltheandroid.roomexample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.cyriltheandroid.roomexample.R
import fr.cyriltheandroid.roomexample.ui.model.ContactUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfContactsView(
    contacts: List<ContactUi>,
    onFABClick: () -> Unit,
    onUpdateContactClick: (Int, String, String, String) -> Unit,
    onDeleteContactClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { ContactTopBar() },
        floatingActionButton = { CreateContactActionButton(onClick = onFABClick) }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(contacts) { index, contact ->
                ContactCardView(
                    contactUi = contact,
                    onUpdateContactClick = { firstName, lastName, phoneNumber ->
                        onUpdateContactClick(index, firstName, lastName, phoneNumber)
                    },
                    onDeleteButtonClick = {
                        onDeleteContactClick(index)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTopBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) }
    )
}

@Composable
fun CreateContactActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        Text(stringResource(id = R.string.plus))
    }
}