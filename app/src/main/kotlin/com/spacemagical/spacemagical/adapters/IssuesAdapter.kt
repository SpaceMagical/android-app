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
import com.spacemagical.spacemagical.models.Issue
import com.spacemagical.spacemagical.models.JobType
import com.spacemagical.spacemagical.models.User
import com.spacemagical.spacemagical.services.JobTypeService
import com.squareup.picasso.Picasso

class IssuesAdapter: RecyclerView.Adapter<IssuesAdapter.ViewHolder> {
    var inflater: LayoutInflater? = null
    var issues: List<Issue>? = null

    constructor(context: Context, issues: List<Issue>): super() {
        inflater = LayoutInflater.from(context)
        this.issues = issues
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): IssuesAdapter.ViewHolder =
        IssuesAdapter.ViewHolder(inflater!!.inflate(R.layout.adapter_issue_item, viewGroup, false))

    override fun onBindViewHolder(viewHolder: IssuesAdapter.ViewHolder, position: Int) {
        val issue = issues?.get(position)
        viewHolder.binding?.setVariable(BR.issue, issue)
        viewHolder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = issues?.size ?: 0

    class ViewHolder: RecyclerView.ViewHolder {
        var binding: ViewDataBinding? = null
        constructor(itemView: View): super(itemView) {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}
