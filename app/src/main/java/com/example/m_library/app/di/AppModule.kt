package com.example.m_library.app.di

import android.app.Application
import androidx.room.Room
import com.example.m_library.app.data.local.BookDatabase
import com.example.m_library.app.data.local.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Book Repository, provide the daos via the db,
    // instead of going to provide daos separately from the DB; this is just using the available resources
    @Singleton
    @Provides
    fun provideBookRepository(bookDatabase: BookDatabase): BookRepository {
        return BookRepository(
            bookDao = bookDatabase.bookDao(),
            newWordDao = bookDatabase.newWordDao()
        )
    }

    //BookDB
    @Singleton
    @Provides
    fun providesDatabase(application: Application): BookDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = BookDatabase::class.java,
            name = BookDatabase::class.simpleName
        ).build()
    }
}
