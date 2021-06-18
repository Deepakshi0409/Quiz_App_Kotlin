package com.example.quiz.database

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.quiz.data.Quiz
import java.util.concurrent.Executors


class QuizRepository(application : Application) {
    private val quizDao : QuizDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val quizDatabase = QuizDatabase.getInstance(application)
        quizDao = quizDatabase.Dao
    }
    companion object{
        private var quizRepository: QuizRepository? = null
        fun getRepository(application: Application): QuizRepository? {
            if (quizRepository==null){
                synchronized(QuizRepository::class.java){
                    if (quizRepository==null){
                        quizRepository = QuizRepository(application)
                    }
                }
            }
            return quizRepository
        }
    }
    fun insertState(quiz: Quiz){
        executorService.execute{
            quizDao.insertState(quiz)
        }
    }
    fun deleteState(quiz: Quiz){
        executorService.execute { quizDao.deleteState(quiz) }
    }

    fun updateState(quiz: Quiz){
        executorService.execute {
            quizDao.updateState(quiz)
        }
    }

    fun getAllStates(): LiveData<PagedList<Quiz>>{
        return LivePagedListBuilder(quizDao.getAllStates(), 15).build()
    }

    fun getAllStates(sortBy: String): LiveData<PagedList<Quiz>>{
        return LivePagedListBuilder(quizDao.getAllStates(constructQuery(sortBy)), 15).build()
    }

    private fun constructQuery(sortBy: String): SupportSQLiteQuery {
        val query = "SELECT * FROM StateAndCapital ORDER BY $sortBy ASC"
        return SimpleSQLiteQuery(query)
    }

    @WorkerThread
    fun getRandomState(): Quiz{
        return quizDao.getRandomState()
    }

    fun getQuizStates(value: Int): LiveData<List<Quiz>>{
        return quizDao.getQuizStates(value)
    }
}