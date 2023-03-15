# M-Library
Most applications have enabled free accessibility to books online. However, there is no book tracking system for the individual.
Therefore, to mitigate this, this mobile application was build. The application helps a user add books there reading/have read/ want to read, and 
the user can clearly see their book collection (library). Moreover, they can get insights of this collection. That is,total books, those to read and completed reads.
### Features of the App
1. Add books using a [Dialog](https://m2.material.io/components/dialogs#full-screen-dialog)
     - The data collected includes:
         - Title of the book
         - Name of author
         - Current Chapter
         - Total Number of Chapters
         - Whether it is read, currently reading or completed reading.
         - Set a goal by date on when to finish the reading.
2. Show all the books in a List using [Lazy Column](https://developer.android.com/jetpack/compose/lists)
3. See the details of each book
4. Display the stats of the whole collection of books
5. Track user progress easily using a ` Determinate Circular Progress Indicator`
