package com.example.unisportinverse.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.ItemSectionBinding

class SectionsAdapter (
    private val newsList: List<GetSectionsResponse>
): RecyclerView.Adapter<SectionsAdapter.ViewHolder>() {

    var onItemClick : ((GetSectionsResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemSectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.binding.apply {
            nameSection.text = currentItem.name
            ageSection.text = "${currentItem.age} лет"
            timeSection.text = currentItem.days
            placeSection.text = currentItem.address
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

}