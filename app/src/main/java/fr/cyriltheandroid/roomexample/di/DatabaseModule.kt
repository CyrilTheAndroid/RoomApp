package fr.cyriltheandroid.roomexample.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.cyriltheandroid.roomexample.data.room.ContactDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideContactDao(contactDatabase: ContactDatabase) = contactDatabase.contactDao()

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext appContext: Context): ContactDatabase {
        return Room.databaseBuilder(appContext, ContactDatabase::class.java, "contacts_db").build()
    }
}