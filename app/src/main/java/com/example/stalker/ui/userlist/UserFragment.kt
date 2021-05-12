package com.example.stalker.ui.userlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stalker.R
import com.example.stalker.StalkerApplication

class UserFragment : Fragment() {

    val adapter = UserListAdapter()
    private lateinit var recyclerview: RecyclerView
    private lateinit var usersViewModel: UserViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        usersViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        if (savedInstanceState == null) {
            usersViewModel.provideDataRepository((activity?.application as StalkerApplication).getUserRepository())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        usersViewModel.observeLiveData().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
