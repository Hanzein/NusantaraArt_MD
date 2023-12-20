package com.capstone.bangkit.NusArt.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bangkit.NusArt.databinding.ItemUserBinding
import com.capstone.bangkit.NusArt.view.detail.DetailActivity
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import com.capstone.bangkit.NusArt.data.remote.response.ListArtItem


class StoryAdapter : PagingDataAdapter<ListArtItem, StoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        if (review != null) {
            holder.bind(review)
        }
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ListArtItem){
            binding.tvItemName.text = user.title
            binding.tvItemGenre.text = user.genre
            Glide.with(binding.root.context)
                .load(user.image)
                .into(binding.image)

            binding.root.setOnClickListener {
                openDetailPage(user)
            }

        }
        private fun openDetailPage(user: ListArtItem){
            val intentDetail = Intent(binding.root.context, DetailActivity::class.java)
            intentDetail.putExtra(TITLE_KEY, user.title)
            intentDetail.putExtra(IMAGE_KEY, user.image)
            intentDetail.putExtra(GENRE_KEY, user.genre)
            intentDetail.putExtra(DESC_KEY, user.description)
            intentDetail.putExtra(ARTIST_KEY, user.artist)
            intentDetail.putExtra(ERA_KEY, user.era)



//            val optionsCompat: ActivityOptionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    binding.root.context as Activity,
//                    Pair(binding.image, "image"),
//                    Pair(binding.tvItemName, "title"),
//                    Pair(binding.tvItemDescription, "description"),
//                )
            itemView.context.startActivity(intentDetail)
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListArtItem>() {
            override fun areItemsTheSame(oldItem: ListArtItem, newItem: ListArtItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListArtItem, newItem: ListArtItem): Boolean {
                return oldItem == newItem
            }
        }

        const val TITLE_KEY = "title"
        const val ARTIST_KEY = "artist"
        const val IMAGE_KEY = "image"
        const val GENRE_KEY = "genre"
        const val ERA_KEY = "era"
        const val DESC_KEY = "description"
    }

}