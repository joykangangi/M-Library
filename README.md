# M-Library
Most applications have enabled free accessibility to books online. However, there is no book tracking system for the individual.
Therefore, to mitigate this, this mobile application was build. The application helps a user add books there reading/have read/ want to read, and 
the user can clearly see their book collection (library). Moreover, they can get insights of this collection. That is,total books, those to read and completed reads.
## Features of the App
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

## Libraries Used
### 1. [Room](https://developer.android.com/topic/libraries/architecture/room)
- This stores the collected data persistently hence providing access to previously recorded data even after closing the application. 
- Room is an abstraction layer on top of a SQLite database. SQLite uses a specialized language (SQL) to perform database operations. 
- Instead of using SQLite directly, room simplifies the chores of database setup, configuration, and interactions with the app.
- Room also provides compile-time checks of SQLite statements.

    - An abstraction layer is a set of functions that hide the underlying implementation/complexity. It provides an interface to an existing set of functionality, like SQLite in this case. 
 - When it comes to persisting data, you need to convert this data into a format compatible with database storage. To do so, you need tables to store the data and queries to access and modify the data.

- The following three components of Room make these workflows seamless.

  1. [Room entities](https://developer.android.com/training/data-storage/room/defining-data) represent tables in your app's database. You use them to update the data stored in rows in tables and to create new rows for insertion.
  2. [Room DAOs](https://developer.android.com/training/data-storage/room/accessing-data) provide methods that your app uses to retrieve, update, insert, and delete data in the database.
  3. [Room Database](https://developer.android.com/reference/kotlin/androidx/room/Database) class is the database class that provides your app with instances of the DAOs associated with that database.  
 - The dependencies of the library are added to the build.gradle (:app module) file as follows:
 ```
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
 ```
  - Personally, I ignored using `ksp` in place of `kapt` as it brought errors in this particular project.
  ### 2. [Navigation]( https://developer.android.com/guide/navigation) 
 - It is a Jetpack library that enables navigating from one destination within your app to another.
 - The app has 5 screens to show, therefore this library is useful for migrating from one screen to the other.
 - Navigation has 3 componenets NavController, NavHost and NavGraph.
 ### 3. [Dependency Injection](https://www.google.com/url?client=internal-element-cse&cx=000521750095050289010:zpcpi1ea4s8&q=https://developer.android.com/training/dependency-injection&sa=U&ved=2ahUKEwjS1Jbao-H9AhXZNsAKHWNLB1sQFnoECAIQAg&usg=AOvVaw2oA-oALv95msY1NWbv3zQe)

 ## How the code works
 The app is divided into packages to do small units of work. These packages include:
 - Converters
 - Data
 - Dependency Injection
 - Model 
 - Navigation
 - UI
 - Util
 - Viewmodel
 ## Run The app
 - Use this link: [Appetize](https://appetize.io/app/brwhzd3d3o25smamxgmc5ml4wu)
 
 ## Challenges Faced
 - Figuring out that the viewmodel should be scoped to a composable and not the whole activity. 
  - Before this, once the user would add a book, they would move away then when then they come back to add another book they would find details of the previous book there. However, this was not an issue in the Edit Screen.
  - After going through [this issue](https://stackoverflow.com/questions/64955859/scoping-states-in-jetpack-compose) in stackoverflow, I learnt on how I could scope my viewmodel to respective composable. This solved the error. Once a user adds a book, that Viewmodel instance would be destroyed. They would find a clear screen after returning to the add screen. 
  - Subsequently, the Edit Screen was blank, because the viewmodel instance was destroyed. To restore the details to be edited I created an event `Restore Details`. "..but you can use the values from `bookstate`?" you might wonder. If I do this I'll have to add code for event and state management similar to the `fun getEvent` in the `BookViewModel. This would cause alot of redundancy and more errors/complexity. So when the user goes to edit, we copy the values of bookstate to detail state. Remember, detail state at this point is empty.
 - In addition to this, I learnt:
   1. Use Store complex data, like `Date` in Room Database.
   2. Using progressIndicators
   3. Hiding Bottom Navigation Bar in certain screen
   4. Splitting `Composables` into small portions
   5. Theming using [Material Theme Builder](https://m3.material.io/theme-builder#/custom)
