package com.muhammedguven.iremember.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.muhammedguven.iremember.data.local.IRememberDatabase
import com.muhammedguven.iremember.data.local.dao.CallLogsDao
import com.muhammedguven.iremember.data.local.dao.ContactsDao
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
    fun provideIRememberDatabase(context: Context): IRememberDatabase {

        return Room.databaseBuilder(
            context,
            IRememberDatabase::class.java, DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideContactsDao(iRememberDatabase: IRememberDatabase): ContactsDao {
        return iRememberDatabase.contactsDao
    }

    @Provides
    @Singleton
    internal fun provideCallLogsDao(iRememberDatabase: IRememberDatabase): CallLogsDao {
        return iRememberDatabase.callLogsDao
    }
}