package com.example.quiz.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quiz.data.Quiz
import com.example.quiz.database.QuizRepository

class AddViewModel(application: Application) : AndroidViewModel(application) {
    var quizRepository = QuizRepository.getRepository(application)!!

    fun insertState(quiz: Quiz){
        quizRepository.insertState(quiz)
    }

    fun updateState(quiz: Quiz){
        quizRepository.updateState(quiz)
    }

}