package com.nitkkr.sanjay.nestedRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.nestedRecyclerView.databinding.ItemChildRecyclerviewBinding

class ChildAdapter(private val list: List<MemberItemResponse>, private val onItemClickListener: ViewHolder.OnItemClickListener) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    class ViewHolder private constructor(private val binding: ItemChildRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnItemClickListener {
            fun onClick(childItemPosition: Int)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(ItemChildRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(position: Int, data: MemberItemResponse, onItemClickListener: OnItemClickListener) {
            binding.member = data
            binding.name.setOnClickListener {
                onItemClickListener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, list[position], onItemClickListener)
    }

    override fun getItemCount(): Int = list.size
}