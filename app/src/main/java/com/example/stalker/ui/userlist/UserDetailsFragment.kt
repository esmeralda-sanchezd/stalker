package com.example.stalker.ui.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stalker.R

private const val ID_VALUE= "ID"

class UserDetailsFragment : Fragment() {
    private var idValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ID_VALUE)?.let {
            idValue = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(idValue: String) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ID_VALUE, idValue)
            }
        }
    }
}