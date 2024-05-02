package com.example.unisportinverse.presentation.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.unisportinverse.data.model.GetRecommendResponse
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.ItemRecommendationBinding
import com.example.unisportinverse.databinding.ItemSectionBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecommendationsAdapter (
    private val recommendList: List<GetRecommendResponse>
): RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    private lateinit var context: Context

    var onItemClick : ((GetRecommendResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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
            Picasso.with(context).load(currentItem.image).into(imageRecommend)
            val beginningDateTime = parseDateTime(currentItem.beginningAt.toString())
            val endingDateTime = parseDateTime(currentItem.endingAt!!)
            dateRecommend.text = formatDateTime(beginningDateTime)
            timeRecommend.text = "${beginningDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${endingDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            holder.itemView.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.link))
                context.startActivity(intent)
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