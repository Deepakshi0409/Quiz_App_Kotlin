package com.example.quiz.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.R
import com.example.quiz.data.Quiz

class ListPagingAdapter : PagedListAdapter<Quiz,ListViewHolder>(DIFF_CALLBACK) {

    private lateinit var clickListener :  ClickListener

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentStates : Quiz ? = getItem(position)
        if(currentStates != null){
            holder.bind(currentStates)
            holder.itemView.setOnClickListener {
                clickListener.onItemClick(position,it)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item,parent,false)
            return ListViewHolder(view)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Quiz>(){
            override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                return oldItem.stateName == newItem.stateName
            }

            override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                    return oldItem==newItem
            }
        }
    }

    fun setItemClickListener(clickListener : ClickListener){
        this.clickListener=clickListener
    }
    public interface ClickListener{
        fun onItemClick(position: Int,view : View)

    }
    fun getStateAtPosition(position: Int): Quiz? {
        return getItem(position)
    }
}