package com.capstone.bangkit.NusArt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.capstone.bangkit.NusArt.R
import com.capstone.bangkit.NusArt.adapter.StoryAdapter
import com.capstone.bangkit.NusArt.databinding.ActivityDetailBinding
import com.capstone.bangkit.NusArt.databinding.ActivityResultScanBinding
import com.capstone.bangkit.NusArt.view.main.MainActivity

class ResultScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genre = intent.getStringExtra(GENRE_KEY)
        val style = intent.getStringExtra(STYLE_KEY)
        val image = intent.getStringExtra(IMAGE_KEY)

        setupAction()

        if (genre != null) {
            binding.tvItemStyle.text = style
            binding.tvItemGenre.text = genre
            Glide.with(binding.root.context)
                .load(image)
                .into(binding.image)
        }
    }

    private fun setupAction() {
        binding.btnBackDetail.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    companion object {
        const val GENRE_KEY = "genre_scan"
        const val IMAGE_KEY = "image_scan"
        const val STYLE_KEY = "style"
    }
}