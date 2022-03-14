package com.nitkkr.sanjay.nestedRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.nestedRecyclerView.databinding.ItemRowBinding

class ParentAdapter(private val parentList: List<HouseResponse>, private val parentClickListener: ParentAdapter.ViewHolder.OnClickListener) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    class ViewHolder private constructor(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(parentPosition: Int, houseResponse: HouseResponse, parentClickListener: OnClickListener, childClickListener: ChildAdapter.ViewHolder.OnItemClickListener) {
            binding.house = houseResponse
            binding.contentTitle.setOnClickListener {
                parentClickListener.onTitleClicked(parentPosition)
            }

            val adapter = ChildAdapter(houseResponse.members!!, object : ChildAdapter.ViewHolder.OnItemClickListener {
                override fun onClick(childItemPosition: Int) {
                    childClickListener.onClick(childItemPosition)
                }
            })
            binding.childRecyclerView.adapter = adapter
        }

        interface OnClickListener {
            fun onTitleClicked(position: Int)
            fun onChildItemClicked(parentPosition: Int, childPosition: Int)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position, parentList[position], parentClickListener, object : ChildAdapter.ViewHolder.OnItemClickListener {
            override fun onClick(childItemPosition: Int) {
                parentClickListener.onChildItemClicked(holder.adapterPosition, childItemPosition)
            }
        })
    }

    override fun getItemCount(): Int = parentList.size
}