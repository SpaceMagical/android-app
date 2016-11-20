package com.spacemagical.spacemagical.components

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.spacemagical.spacemagical.adapters.IssuesAdapter
import com.spacemagical.spacemagical.models.Issue

class IssuesListCard : ListCard {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    fun setIssues(issues: List<Issue>) {
        setAdapter(IssuesAdapter(context, issues) as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }
}
