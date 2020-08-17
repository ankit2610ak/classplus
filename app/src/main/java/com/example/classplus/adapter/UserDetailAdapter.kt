package com.example.classplus.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.classplus.MainActivity
import com.example.classplus.R
import com.example.classplus.model.UserDetails
import com.example.classplus.ui.repo.RepoFragment

class UserDetailAdapter(
     val context: Context) :
    PagedListAdapter<UserDetails, UserDetailAdapter.CustomViewHolder>(USER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.list_item_users, parent, false)
        )

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }

    }

   inner class CustomViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var userPhoto: ImageView = itemLayoutView.findViewById(R.id.userPhoto)
        var login: TextView = itemLayoutView.findViewById(R.id.login)
        var type: TextView = itemLayoutView.findViewById(R.id.type)
        var cardView: ConstraintLayout = itemLayoutView.findViewById(R.id.user_layout)

        fun bind(user: UserDetails) {
            login.text = user.login
            type.text = user.type
            Glide.with(userPhoto.context)
                .load(user.avatar_url)
                .into(userPhoto)
            cardView.setOnClickListener {
                val fragment = RepoFragment.newInstance()
                val bundle = Bundle()
                bundle.putString("login", user.login)
                fragment.arguments = bundle
                (context as MainActivity).addFragment(fragment)
            }
        }

   }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserDetails>() {
            override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean =
                newItem == oldItem
        }
    }

}
