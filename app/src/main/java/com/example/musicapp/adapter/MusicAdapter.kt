package com.example.musicapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.model.ResultSong
import com.example.musicapp.databinding.FragmentViewBinding
import com.example.musicapp.databinding.MusicItemBinding
import com.squareup.picasso.Picasso

class MusicAdapter(
    private val itemSet: MutableList<ResultSong> = mutableListOf(),
    private val onItemClick: (previewUrl: String) -> Unit
) : RecyclerView.Adapter<MusicAdapter.SongViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ResultSong>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

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


    class SongViewHolder(private val binding: MusicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ResultSong, onItemClick: (String) -> Unit) {

            Picasso.get().load(item.artworkUrl60).into(binding.imageViewGenre)
            binding.tvArtistName?.text  = item.artistName
            binding.tvCollection?.text = item.collectionName
            binding.tvPrice?.text = "$${item.trackPrice.toString()} USD"


            itemView.setOnClickListener {
                item.previewUrl?.let(onItemClick)
            }
        }

    }
}