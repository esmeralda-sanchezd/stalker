package com.example.stalker.ui.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stalker.R
import com.example.stalker.StalkerApplication
import com.example.stalker.databinding.FragmentUserDetailsBinding
import com.example.stalker.util.loadFromUrl

private const val ID_VALUE= "ID"

class UserDetailsFragment : Fragment() {
    private var idValue: String? = null
    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var userDetailsBinding : FragmentUserDetailsBinding

    val name by lazy { userDetailsBinding.name }
    val email by lazy { view?.findViewById<TextView>(R.id.email) }
    val phone by lazy { view?.findViewById<TextView>(R.id.phone) }
    val photo by lazy { view?.findViewById<ImageView>(R.id.user_photo)}

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
        userDetailsBinding = FragmentUserDetailsBinding.inflate(inflater, container, false);
         userDetailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)
        if(savedInstanceState == null){
            (activity?.application as? StalkerApplication)?.getUserRepository()?.let {
                userDetailsViewModel.provideDataRepository(
                    it
                )
            }
        }
        return userDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            idValue?.let { userDetailsViewModel.fetchUser(it) }
        }
        userDetailsViewModel.observeLiveData().observe(viewLifecycleOwner, Observer {

            userDetailsBinding.name.text  = "${it.name.first} ${it.name.last}"
            userDetailsBinding.email.text = "${it.email}"
            userDetailsBinding.phone.text  = "${it.phone}"
            userDetailsBinding.userPhoto.loadFromUrl(it.picture.large)

            Log.d("FETCH_CACHE",  "${it}")
        })
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