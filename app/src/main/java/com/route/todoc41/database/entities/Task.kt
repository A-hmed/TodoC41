package com.route.todoc41.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val date: Long,
    @ColumnInfo
    var isDone: Boolean,
    @ColumnInfo
    val description: String,
)