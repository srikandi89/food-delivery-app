package com.srikandi.common.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GeneralRecyclerviewAdapter<T>(
    @LayoutRes val layoutResId: Int,
    private val onBind: (T, position: Int, View) -> Unit,
    private val itemListener: (T, position: Int, View) -> Unit
): RecyclerView.Adapter<GeneralRecyclerviewViewHolder<T>>() {
    private val diffCallback = DiffCallback<T>()
    private val list = mutableListOf<T>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralRecyclerviewViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(
            layoutResId,
            parent,
            false
        )

        return GeneralRecyclerviewViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GeneralRecyclerviewViewHolder<T>, position: Int) {
        holder.bindView(list[position], onBind, itemListener)
    }

    fun setData(newList: List<T>) {
        calculateDiff(newList)
    }

    private fun calculateDiff(newList: List<T>) {
        diffCallback.setList(list, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(list) {
            clear()
            addAll(newList)
        }
        result.dispatchUpdatesTo(this)
    }

}