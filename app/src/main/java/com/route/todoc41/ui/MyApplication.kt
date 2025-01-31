package com.route.todoc41.ui

import android.app.Application
import com.route.todoc41.database.MyDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.initDatabase(this)
    }

}