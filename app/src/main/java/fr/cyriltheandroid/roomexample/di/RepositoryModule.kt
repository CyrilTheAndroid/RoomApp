package fr.cyriltheandroid.roomexample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.cyriltheandroid.roomexample.data.repository.ContactRepository
import fr.cyriltheandroid.roomexample.data.repository.ContactRepositoryImpl
import fr.cyriltheandroid.roomexample.data.service.ContactDatabaseService
import fr.cyriltheandroid.roomexample.data.service.ContactDatabaseServiceImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindContactDatabaseService(
        contactDatabaseServiceImpl: ContactDatabaseServiceImpl
    ): ContactDatabaseService

    @Binds
    abstract fun bindContactRepository(
        contactRepositoryImpl: ContactRepositoryImpl
    ): ContactRepository
}