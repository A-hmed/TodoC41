package com.route.todoc41.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todoc41.database.dao.TasksDao
import com.route.todoc41.database.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    companion object {
        private var database: MyDatabase? = null
        fun initDatabase(context: Context) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "Tasks database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
            }
        }

        fun getInstance(): MyDatabase {
            return database!!
        }
    }
}
//Singleton Design - Builder - Factory - Abstract factory