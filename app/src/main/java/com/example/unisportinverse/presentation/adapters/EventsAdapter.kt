package com.example.unisportinverse.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unisportinverse.data.model.GetEventsResponse
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.ItemEventsBinding
import com.example.unisportinverse.databinding.ItemSectionBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventsAdapter (
    private val eventsList: List<GetEventsResponse>
): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    var onItemClick : ((GetEventsResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemEventsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.binding.apply {
            nameEvents.text = currentItem.name
            place.text = currentItem.description
            val beginningDateTime = parseDateTime(currentItem.beginningAt.toString())
            dateEvents.text = formatDateTime(beginningDateTime)
//            holder.itemView.setOnClickListener{
//                onItemClick?.invoke(currentItem)
//            }
        }
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
        return "$date"
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

}