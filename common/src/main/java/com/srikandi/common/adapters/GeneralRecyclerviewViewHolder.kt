package com.srikandi.common.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GeneralRecyclerviewViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(
        item: T,
        onBind: (T, View) -> Unit,
        itemListener: (T, position: Int, View) -> Unit
    ) {
        with(itemView) {
            onBind.invoke(item, this)
            setOnClickListener {
                itemListener.invoke(item, adapterPosition, this)
            }
        }
    }
}