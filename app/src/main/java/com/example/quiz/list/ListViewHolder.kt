package com.example.quiz.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.data.Quiz
import com.example.quiz.R
import kotlinx.android.synthetic.main.list_item.view.*

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var stateName = itemView.stateTV
        private var capitalName = itemView.capitalTV

    fun bind(quiz: Quiz){
        stateName.text = quiz.stateName
        capitalName.text = quiz.capitalName
    }
}