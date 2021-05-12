package com.example.stalker.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.stalker.R
import com.example.stalker.api.models.Id
import com.example.stalker.api.models.User

class UserListAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<User, UserListAdapter.UserViewHolder>(
        UserDiffCallback
    ) {
    class UserViewHolder(itemView: View, val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val email: TextView = itemView.findViewById(R.id.email)
        private val phone: TextView = itemView.findViewById(R.id.phone)
        private val photo: ImageView = itemView.findViewById(R.id.user_photo)


        /* Bind flower name and image. */
        fun bind(user: User) {
            name.text = "${user.name.first} ${user.name.last}"
            email.text = user.email
            phone.text = user.phone

            itemView.setOnClickListener {
                onClick(user.id.value)
            }

            var uri = user.picture.large
            Glide.with(itemView.context)
                .load(uri)
                .transform(CircleCrop())
                .into(photo)
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(
            view, onClick
        )
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }
}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
