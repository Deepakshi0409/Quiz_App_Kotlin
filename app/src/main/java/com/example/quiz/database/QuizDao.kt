package com.example.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.quiz.data.Quiz
import androidx.paging.DataSource

@Dao
interface QuizDao {

    @Query("SELECT * FROM state_table")
    fun getAllStates(): DataSource.Factory<Int,Quiz>
    @Insert
    fun insertState(quiz: Quiz)

    @Delete
    fun deleteState(quiz: Quiz)

    @Update
    fun updateState(quiz: Quiz)

   @RawQuery(observedEntities = [Quiz::class])
   fun getAllStates(supportSQLiteQuery : SupportSQLiteQuery) : DataSource.Factory<Int,Quiz>

   @Query("SELECT * FROM state_table ORDER BY RANDOM() LIMIT :value")
   fun getQuizStates(value:Int):LiveData<List<Quiz>>

   @Query("SELECT * FROM state_table ORDER BY RANDOM() LIMIT 1")
   fun getRandomState() : Quiz
    
}