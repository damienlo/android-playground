package com.vito.cornelius.feature.home.users.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vito.cornelius.feature.home.R
import com.vito.cornelius.feature.home.databinding.ItemListUserBinding
import com.vito.cornelius.feature.home.users.list.UserListAdapter.UsersViewHolder
import com.vito.cornelius.feature.home.users.model.UserUiModel

class UserListAdapter(
        private val artistSelectedListener: UserSelectedListener
) : RecyclerView.Adapter<UsersViewHolder>() {

    private val data: ArrayList<UserUiModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListUserBinding.inflate(layoutInflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = data[position]
        holder.bind(user)
    }

    fun setData(users: List<UserUiModel>) {
        with(data) {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

        private val userNameTextView: TextView = binding.userNameTextView
        private val userImageView: ImageView = binding.userImageView
        private val containerLayout: LinearLayout = binding.userLayout

        fun bind(user: UserUiModel) {
            userNameTextView.text = user.name
            with(userImageView) {
                transitionName = user.avatar
                loadAvatar(user.avatar)
            }
            containerLayout.setOnClickListener {
                artistSelectedListener.onUserSelected(user, userImageView)
            }
        }

        private fun ImageView.loadAvatar(url: String?) {
            Glide.with(context)
                    .load(url)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.drawable.ic_outline_person_outline_24)
                                    .error(R.drawable.ic_outline_person_outline_24)
                                    .circleCrop()
                    )
                    .into(this)
        }
    }

    interface UserSelectedListener {
        fun onUserSelected(user: UserUiModel, imageView: ImageView)
    }
}