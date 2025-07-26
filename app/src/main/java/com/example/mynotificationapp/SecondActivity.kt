package com.example.mynotificationapp // Replace with your actual package name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mynotificationapp.Chat1Fragment
import com.example.mynotificationapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load the default fragment on app start
        loadFragment(Chat1Fragment())

        // Set up the bottom navigation bar
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chat -> {
                    loadFragment(Chat1Fragment())
                    true
                }
                R.id.status -> {
                    loadFragment(StatusFragment())
                    true
                }
                R.id.groups -> {
                    loadFragment(GroupFragment())
                    true
                }
                R.id.calls -> {
                    loadFragment(CallsFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Function to load fragments into the FrameLayout
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView3, fragment)
            .commit()
    }
}
