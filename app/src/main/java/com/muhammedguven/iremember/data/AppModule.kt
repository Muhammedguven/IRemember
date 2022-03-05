package com.muhammedguven.iremember.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.muhammedguven.iremember.local.IRememberDatabase
import com.muhammedguven.iremember.local.dao.ContactsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "iremember.db"

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideIRememberDatabase(context: Context): IRememberDatabase =
        Room
            .databaseBuilder(context, IRememberDatabase::class.java, DB_NAME)
            .build()

    @Provides
    @Singleton
    internal fun providePreviouslySearchedItemDao(iRememberDatabase: IRememberDatabase): ContactsDao {
        return iRememberDatabase.contactsDao
    }
}