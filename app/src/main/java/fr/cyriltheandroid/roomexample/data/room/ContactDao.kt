package fr.cyriltheandroid.roomexample.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    fun addNewContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts")
    fun getContacts() : Flow<List<ContactEntity>>

    @Update
    fun updateContact(contactEntity: ContactEntity)

    @Query("DELETE FROM contacts WHERE id = :idContact")
    fun deleteContact(idContact: Long)
}