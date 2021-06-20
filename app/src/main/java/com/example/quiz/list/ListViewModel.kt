package com.example.quiz.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.example.quiz.data.Quiz
import com.example.quiz.database.QuizRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private var quizRepository = QuizRepository.getRepository(application)!!
    var statesList : LiveData<PagedList<Quiz>>

    init {
        statesList = quizRepository.getAllStates()
    }
    fun insertState(quiz: Quiz){
        quizRepository.insertState(quiz)
    }

    fun deleteState(quiz: Quiz){
        quizRepository.deleteState(quiz)
    }

}
