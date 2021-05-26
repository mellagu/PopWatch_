package com.mellagusty.movieapppopcorn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mellagusty.movieapppopcorn.R
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.databinding.ItemListContainerBinding

class PopWatchPagedAdapter(private val listener: (Poster) -> Unit) :
    PagedListAdapter<Poster, PopWatchPagedAdapter.CardViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Poster>() {
            override fun areItemsTheSame(oldItem: Poster, newItem: Poster): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Poster, newItem: Poster): Boolean {
                return oldItem == newItem
            }
        }

    }


    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(popwatch: Poster) {

            itemView.setOnClickListener {
                listener(popwatch)
            }
            val binding = ItemListContainerBinding.bind(itemView)
            val txtTitle = binding.tvTitleCard
            val txtDateCard = binding.tvDateCard
            val imgPoster = binding.ivCard


            Glide.with(itemView.context)
                .load(popwatch.baseUrl + popwatch.poster_path)
                .into(imgPoster)

            txtTitle.text = popwatch.original_title ?: popwatch.original_name
            txtDateCard.text = popwatch.release_date ?: popwatch.first_air_date

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_container, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val pop = getItem(position)
        pop?.let { holder.bind(it) }
    }


}

