    package com.example.quiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quiz.data.Quiz
import java.util.concurrent.Executors

    @Database(entities = [Quiz::class],version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {
    abstract val Dao: QuizDao


    companion object {
        val executorService = Executors.newSingleThreadExecutor()

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance( context: Context) : QuizDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(context.applicationContext, QuizDatabase::class.java, "QuizDatabase")
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                executorService.execute {
                                    populateDb(context.assets, getInstance(context))
                                }
                            }
                        })
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}