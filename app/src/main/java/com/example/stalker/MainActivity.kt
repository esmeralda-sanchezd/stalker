package com.example.stalker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stalker.ui.userlist.UserFragment

class MainActivity : AppCompatActivity() {
    val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        //Initial commit
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, userFragment)
                .commitAllowingStateLoss()
        }
    }
}