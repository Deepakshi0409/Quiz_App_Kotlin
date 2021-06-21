package com.example.quiz.notification

import android.app.Application
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quiz.database.QuizRepository

class NotificationsWorker (context : Context, workerParameters: WorkerParameters) : Worker(context.applicationContext, workerParameters){
    private val quizRepo : QuizRepository = QuizRepository.getRepository(context.applicationContext as Application)!!
    private val notifications : Notifications = Notifications()


    override fun doWork(): Result {
        var quiz = quizRepo.getRandomState()
        notifications.getDailyNotifications(applicationContext, quiz)
        return Result.success()
    }
}