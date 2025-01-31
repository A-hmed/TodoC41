package com.route.todoc41.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todoc41.database.entities.Task

@Dao
interface TasksDao {
    @Query("SELECT * from Task")
    fun getAllTasks(): List<Task>

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * from Task where date = :date ")
    fun getTasksByDate(date: Long): List<Task>

    @Insert
    fun addTask(task: Task)
}