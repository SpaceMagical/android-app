package com.spacemagical.spacemagical.adapters

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.spacemagical.spacemagical.BR
import com.spacemagical.spacemagical.R
import com.spacemagical.spacemagical.models.Space
import com.spacemagical.spacemagical.presenters.SpaceAdapterPresenter
import com.squareup.picasso.Picasso

class SpacesAdapter: RecyclerView.Adapter<SpacesAdapter.ViewHolder> {
    var inflater: LayoutInflater? = null
    var spaces: List<Space>? = null
    var presenter: SpaceAdapterPresenter? = null

    constructor(context: Context, spaces: List<Space>, presenter: SpaceAdapterPresenter): super() {
        inflater = LayoutInflater.from(context)
        this.spaces = spaces
        this.presenter = presenter
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SpacesAdapter.ViewHolder =
        SpacesAdapter.ViewHolder(inflater!!.inflate(R.layout.adapter_space_item, viewGroup, false))

    override fun onBindViewHolder(viewHolder: SpacesAdapter.ViewHolder, position: Int) {
        val space = spaces?.get(position)
        Log.i("view", space?.name)
        viewHolder.binding?.setVariable(BR.space, space)
        viewHolder.binding?.setVariable(BR.presenter, presenter)
        viewHolder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = spaces?.size ?: 0

    object SpacesAdapterBinding {
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
