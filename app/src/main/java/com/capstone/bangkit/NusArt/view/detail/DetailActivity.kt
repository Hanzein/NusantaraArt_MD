package com.capstone.bangkit.NusArt.view.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.bangkit.NusArt.adapter.StoryAdapter
import com.capstone.bangkit.NusArt.databinding.ActivityDetailBinding
import com.capstone.bangkit.NusArt.view.main.MainActivity


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra(StoryAdapter.TITLE_KEY)
        val photoUrl = intent.getStringExtra(StoryAdapter.IMAGE_KEY)
        val genre = intent.getStringExtra(StoryAdapter.GENRE_KEY)
        val desc = intent.getStringExtra(StoryAdapter.DESC_KEY)
        val artist = intent.getStringExtra(StoryAdapter.ARTIST_KEY)
        val era = intent.getStringExtra(StoryAdapter.ERA_KEY)


        setupAction()

        if (user != null) {
            binding.tvItemName.text = user
            binding.tvItemDescription.text = desc
            binding.tvItemArtist.text = artist
            binding.tvItemEra.text = era
            binding.tvItemGenre.text = genre
            Glide.with(binding.root.context)
                .load(photoUrl)
                .into(binding.image)
        }
    }

    private fun setupAction() {
        binding.btnBackDetail.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

//    companion object {
//        const val NAME_KEY = "name"
//        const val IMAGE_KEY = "image"
//        const val DESC_KEY = "description"
//    }
}