package com.example.m_library.editbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.data.local.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditBookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {
    private val _book = MutableStateFlow(Book())

    val state = _book.map { book ->
        val titleError = if (book.title.length < 5) {
            R.string.title_err
        } else null
        val authorError = if (book.author.length < 5) {
            R.string.auth_err
        } else null
        val chaptersError =
            if (book.currentChapter <= book.totalChapters && (book.totalChapters != 0 || book.currentChapter != 0)) {
                null
            } else R.string.valid_chp

        EditBookState(
            book = book,
            titleError = titleError,
            authorError = authorError,
            chaptersError = chaptersError,
            buttonEnabled = titleError == null && authorError == null && chaptersError == null,
        )
    }.flowOn(Dispatchers.Default).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        EditBookState(),
    )

    fun getBook(bookId: Long?) {
        viewModelScope.launch {
            _book.update {
                when (bookId) {
                    null -> Book()
                    else -> repository.getBook(bookId) ?: Book()
                }
            }
        }
    }

    fun updateBook(book: Book) {
        _book.update { book }
    }

    fun save() {
        viewModelScope.launch {
            repository.insertBook(_book.value)
        }
    }
}