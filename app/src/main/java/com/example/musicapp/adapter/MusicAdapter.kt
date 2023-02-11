package com.example.musicapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.model.ResultSong
import com.example.musicapp.databinding.FragmentViewBinding
import com.example.musicapp.databinding.MusicItemBinding
import com.squareup.picasso.Picasso

private const val TAG = "MusicAdapter"

class MusicAdapter(
    private val itemSet: MutableList<ResultSong> = mutableListOf(),
    private val onItemClick: (previewUrl: String) -> Unit
) : RecyclerView.Adapter<MusicAdapter.SongViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ResultSong>, context: Context) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Toast.makeText(context, "Found ${newItems.count()} Results", Toast.LENGTH_SHORT).show()

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size

    class SongViewHolder(
        private val binding: MusicItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {


        //Binding the view in the cardView
        fun bind(item: ResultSong, onItemClick: (String) -> Unit) {

            binding.tvArtistName.text = item.artistName.toString()
            binding.tvCollection.text = item.collectionName.toString()
            if (item.trackPrice != null) {
                binding.tvPrice.text = "$${item.trackPrice} USD"
            } else {
                binding.tvPrice.text = "Free"
            }
            Picasso.get().load(item.artworkUrl60).into(binding.imageViewGenre)

            //Click on the item an play the review in Exoplayer
            itemView.setOnClickListener {
                item.previewUrl?.let(onItemClick)
            }
        }


    }
}