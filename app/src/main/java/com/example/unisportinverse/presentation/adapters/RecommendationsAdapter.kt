package com.example.unisportinverse.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unisportinverse.data.model.GetRecommendResponse
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.ItemRecommendationBinding
import com.example.unisportinverse.databinding.ItemSectionBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecommendationsAdapter (
    private val recommendList: List<GetRecommendResponse>
): RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    var onItemClick : ((GetRecommendResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recommendList[position]
        holder.binding.apply {
            nameRecommend.text = currentItem.title
            descriptionRecommend.text = currentItem.text
            priceRecommend.text = currentItem.price
            val beginningDateTime = parseDateTime(currentItem.beginningAt.toString())
            val endingDateTime = parseDateTime(currentItem.endingAt!!)
            dateRecommend.text = formatDateTime(beginningDateTime)
            timeRecommend.text = "${beginningDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${endingDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            holder.itemView.setOnClickListener{
                //onItemClick?.invoke(currentItem)
            }
        }
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
        return "$date"
    }

}