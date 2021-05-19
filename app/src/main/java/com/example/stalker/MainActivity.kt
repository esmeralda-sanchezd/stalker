package com.example.stalker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stalker.databinding.ActivityMainBinding
import com.example.stalker.ui.userlist.UserDetailsFragment
import com.example.stalker.ui.userlist.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Initial commit
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserFragment())
                .commit()
        }
    }

    fun navigateToDetailsFragment(userId: String){
        val detailsFragment = UserDetailsFragment.newInstance(userId)
          supportFragmentManager.beginTransaction()
              .replace(R.id.fragment_container, detailsFragment)
              .addToBackStack(null)
              .commit()

    }
}