package com.example.stalker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class UserFragment : Fragment() {

    val adapter = UserListAdapter()
    private lateinit var recyclerview : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview= view.findViewById(R.id.recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

       (activity?.application as StalkerApplication).getUserApi().getUsers()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({
           adapter.submitList(it.results)
          Log.d("DEBUG_USER", "user list = ${it}")
       },{
           Log.e("DEBUG_ERROR", "error ",it)
       })
    }
}