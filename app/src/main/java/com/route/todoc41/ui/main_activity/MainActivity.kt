package com.route.todoc41.ui.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.todoc41.R
import com.route.todoc41.databinding.ActivityMainBinding
import com.route.todoc41.ui.add_task_fragment.AddTaskFragment
import com.route.todoc41.ui.main_activity.fragments.SettingsFragment
import com.route.todoc41.ui.main_activity.fragments.TasksListFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var tasksFragment = TasksListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(tasksFragment)
        initListeners()
    }

    private fun initListeners() {
        binding.fab.setOnClickListener {
            AddTaskFragment({
                tasksFragment.refreshTasksList()
            }).show(supportFragmentManager, null)
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.listTab) {
                showFragment(tasksFragment)
            } else {
                showFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}