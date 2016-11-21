package com.spacemagical.spacemagical.components

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.spacemagical.spacemagical.R

open class EmbeddedList : LinearLayout {
    var container: View? = null

    init {
        container = inflate(context, R.layout.component_embedded_list, this)
        val list = container?.findViewById(R.id.list) as RecyclerView?
        list?.layoutManager = LinearLayoutManager(context)
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        val usersList: RecyclerView? = container?.findViewById(R.id.list) as RecyclerView?
        usersList?.adapter = adapter
        usersList?.adapter?.notifyDataSetChanged()
    }

    fun setTitle(title: String) {
        val cardTitle: TextView? = container?.findViewById(R.id.cardTitle) as TextView?
        cardTitle?.text = title
    }
}
