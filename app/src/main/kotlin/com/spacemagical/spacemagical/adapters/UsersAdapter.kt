package com.spacemagical.spacemagical.adapters

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.spacemagical.spacemagical.BR
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.models.User
import com.squareup.picasso.Picasso

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    var inflater: LayoutInflater? = null
    var users: List<User>? = null

    constructor(context: Context, users: List<User>): super() {
        inflater = LayoutInflater.from(context)
        this.users = users
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UsersAdapter.ViewHolder =
        UsersAdapter.ViewHolder(inflater!!.inflate(R.layout.adapter_user_item, viewGroup, false))

    override fun onBindViewHolder(viewHolder: UsersAdapter.ViewHolder, position: Int) {
        val user = users?.get(position)
        viewHolder.binding?.setVariable(BR.user, user)
        viewHolder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = users?.size ?: 0

    object UsersAdapterBinding {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
    }

    class ViewHolder: RecyclerView.ViewHolder {
        var binding: ViewDataBinding? = null
        constructor(itemView: View): super(itemView) {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}
