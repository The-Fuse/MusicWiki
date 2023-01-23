package com.rohit.musicwiki.ui.genreDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohit.musicwiki.databinding.TabItemBinding
import com.rohit.musicwiki.databinding.TagItemBinding
import com.rohit.musicwiki.models.TabItem
import com.rohit.musicwiki.models.Tag


class TabsRecyclerAdapter(private val clickListener: TabsClickListener) :
    ListAdapter<TabItem, TabsRecyclerAdapter.ViewHolder>(TabsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder private constructor(private val binding: TabItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TabItem, clickListener: TabsClickListener) {
            binding.tabItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TabItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class TabsDiffCallback : DiffUtil.ItemCallback<TabItem>() {
    override fun areItemsTheSame(oldItem: TabItem, newItem: TabItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: TabItem, newItem: TabItem): Boolean {
        return oldItem.artistName == newItem.artistName
    }
}

class TabsClickListener(val clickListener: (TabItem: TabItem)-> Unit) {
    fun onClick(TabItem: TabItem) = clickListener(TabItem)
}