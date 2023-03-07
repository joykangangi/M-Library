package com.example.m_library.di

import android.app.Application
import com.example.m_library.data.BookRepository
import com.example.m_library.data.local.BookDB
import com.example.m_library.data.local.BookDao
import com.example.m_library.data.local.NewWordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    //Book Repository
    @Singleton
    @Provides
    fun provideBookRepository(bookDao: BookDao, newWordDao: NewWordDao): BookRepository {
        return BookRepository(bookDao = bookDao, newWordDao = newWordDao)
    }

    //BookDB
    @Singleton
    @Provides
    fun providesDatabase(application: Application):BookDB{
        return BookDB.getDatabase(context = application)
    }

    //BookDao
    @Singleton
    @Provides
    fun provideBookDao(appDatabase: BookDB): BookDao {
        return appDatabase.bookDao()
    }

    //NewWordDao
    @Singleton
    @Provides
    fun provideNewWordDao(appDatabase: BookDB): NewWordDao {
        return appDatabase.newWordDao()
    }
}
