package com.route.todoc41.ui.main_activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todoc41.database.entities.Task
import com.route.todoc41.databinding.ItemTodoBinding

class TasksAdapter(
    var tasksList: List<Task>,
    val onTaskClick: OnTaskClickListener
) : Adapter<TasksAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
        return TasksViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task = tasksList[position]
        holder.binding.todoTitleTv.text = task.title
        holder.binding.todoDescriptionTv.text = task.description
        holder.binding.deleteIcon.setOnClickListener {
            onTaskClick.onDeleteClick(task)
        }
        holder.binding.icDone.setOnClickListener {
            onTaskClick.onDoneClick(task)
        }
        holder.binding.materialCardView.setOnClickListener {
            onTaskClick.onItemClick(task)
        }

    }

    fun submitList(newTasksList: List<Task>) {
        tasksList = newTasksList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tasksList.size
    class TasksViewHolder(val binding: ItemTodoBinding) : ViewHolder(binding.root)

    interface OnTaskClickListener {
        fun onDeleteClick(task: Task)
        fun onDoneClick(task: Task)
        fun onItemClick(task: Task)
    }
}