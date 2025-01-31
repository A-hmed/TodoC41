package com.route.todoc41.ui.main_activity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todoc41.database.MyDatabase
import com.route.todoc41.database.entities.Task
import com.route.todoc41.databinding.FragmentTasksListBinding
import com.route.todoc41.ui.main_activity.adapters.TasksAdapter
import com.route.todoc41.ui.utils.toMillSeconds

class TasksListFragment : Fragment() {
    lateinit var adapter: TasksAdapter
    lateinit var binding: FragmentTasksListBinding
    var selectedDay = CalendarDay.today()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("onViewCreated", selectedDay.toMillSeconds().toString())
        initListners()
        initRecyclerView()
        initCalendar()
    }

    private fun initCalendar() {
        binding.calendarView.selectedDate = selectedDay
        binding.calendarView.setOnDateChangedListener { _, date, selected ->
            selectedDay = date
            refreshTasksList()
        }
    }

    private fun initRecyclerView() {
        adapter = TasksAdapter(getTasksListFromRoom(), object : TasksAdapter.OnTaskClickListener {
            override fun onDeleteClick(task: Task) {
                MyDatabase.getInstance().getTasksDao().deleteTask(task)
                refreshTasksList()
            }

            override fun onDoneClick(task: Task) {
                task.isDone = !task.isDone
                MyDatabase.getInstance().getTasksDao().updateTask(task)
            }

            override fun onItemClick(task: Task) {
                TODO("Not yet implemented")
            }

        })
        binding.tasksRecyclerView.adapter = adapter
    }

    fun refreshTasksList() {
        val tasksList = getTasksListFromRoom()
        adapter.submitList(tasksList)
    }

    fun getTasksListFromRoom(): List<Task> {
        var tasksList = MyDatabase.getInstance().getTasksDao()
            .getTasksByDate(selectedDay.toMillSeconds())
//        tasksList = tasksList.filter {
//            var taskDate = Calendar.getInstance()
//            taskDate.timeInMillis = it.date
//            return@filter taskDate.year() == selectedDay.year &&
//                    taskDate.month() == selectedDay.month - 1 &&
//                    taskDate.day() == selectedDay.day
//        }
//        tasksList.sortedBy {
//            return@sortedBy it.date
//        }
        return tasksList
    }

    private fun initListners() {
    }
}