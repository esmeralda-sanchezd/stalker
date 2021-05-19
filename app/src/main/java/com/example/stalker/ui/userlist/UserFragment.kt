package com.example.stalker.ui.userlist

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stalker.MainActivity
import com.example.stalker.R
import com.example.stalker.StalkerApplication
import com.example.stalker.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    val adapter = UserListAdapter { value -> adapterOnClick(value) }
    private lateinit var recyclerview: RecyclerView
    private lateinit var usersViewModel: UserViewModel
    private lateinit var userFragmentBinding : FragmentUserBinding;

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userFragmentBinding = FragmentUserBinding.inflate(inflater, container, false);
        val view = userFragmentBinding.root
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

    private fun adapterOnClick(userId: String) {
       (activity as? MainActivity)?.navigateToDetailsFragment(userId)
        Log.d("FETCH_CACHE", "Clicked the tab ${userId}")
    }
}
