package com.rohit.musicwiki.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohit.musicwiki.databinding.TagItemBinding
import com.rohit.musicwiki.models.Tag


class TagsRecyclerAdapter(private val clickListener: TagsClickListener) :
    ListAdapter<Tag, TagsRecyclerAdapter.ViewHolder>(TagsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder private constructor(private val binding: TagItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tag, clickListener: TagsClickListener) {
            binding.tagsItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TagItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class TagsDiffCallback : DiffUtil.ItemCallback<Tag>() {
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.name == newItem.name
    }
}

class TagsClickListener(val clickListener: (tagItem: Tag)-> Unit) {
    fun onClick(tagItem: Tag) = clickListener(tagItem)
}