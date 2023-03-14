package com.example.m_library.archived

/**
 * Files to add later
 */
/*
         Chip(
             modifier = modifier.padding(start = 9.dp, bottom = 3.dp),
             onClick = {
               bookViewModel.getBooksEvent(BookListEvents.DeadLineChanged(deadLine = !bookListState.isDeadLine))
                 Log.i("AllMyBooks","${bookListState.isDeadLine}")
         }, shape = CircleShape) {
             if (bookListState.isDeadLine) {
                 Image(
                     imageVector = Icons.Filled.CheckCircle,
                     contentDescription = stringResource(id = R.string.deadline)
                 )
             }
             Text(text = stringResource(id = R.string.deadLn), fontSize = 16.sp)

         }
         Divider()

         */



/*
    fun getBooksEvent(event: BookListEvents) = viewModelScope.launch {
        when(event) {
            is BookListEvents.DeadLineChanged -> {
                _bookListState.value = _bookListState.value.copy(isDeadLine = !bookListState.value.isDeadLine)
                if (_bookListState.value.isDeadLine) {
                    _bookListState.value= _bookListState.value.copy(bookList = sortByDates().first())
                }
                else {
                    _bookListState.value = _bookListState.value.copy(bookList = bookRepository.getAllBooks().first() )
                }
            }
        }
    }*/

/* private fun getBooks() = viewModelScope.launch{
     _bookListState.value = _bookListState.value.copy(bookList = bookRepository.getAllBooks().first() )
     if (_bookListState.value.isDeadLine) {
         sortByDates()
     }
     Log.i("ALL book VM"," ${_bookListState.value}")
 }
 */