package com.example.m_library.di

import android.app.Application
import androidx.room.Room
import com.example.m_library.data.BookRepository
import com.example.m_library.data.local.BookDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    //Book Repository, provide the daos via the db, instead of going to provide daos separatly from the DB; this is just using the available resources
    @Singleton
    @Provides
    fun provideBookRepository(bookDB: BookDB): BookRepository {
        return BookRepository(bookDao = bookDB.bookDao())
    }

    //BookDB
    @Singleton
    @Provides
    fun providesDatabase(application: Application):BookDB{
        return Room.databaseBuilder(
            context = application,
            klass = BookDB::class.java,
            name = BookDB.DATABASE_NAME
        ).build()
    }
}
