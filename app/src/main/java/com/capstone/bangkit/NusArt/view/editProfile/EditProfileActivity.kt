package com.capstone.bangkit.NusArt.view.editProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import com.capstone.bangkit.NusArt.R
import com.capstone.bangkit.NusArt.databinding.ActivityEditProfileBinding
import com.capstone.bangkit.NusArt.databinding.ActivityLoginBinding
import com.capstone.bangkit.NusArt.view.fragment.profile.ProfileFragment
import com.capstone.bangkit.NusArt.view.main.MainActivity
import com.capstone.bangkit.NusArt.view.welcome.WelcomeActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }
    private fun setupAction() {
        binding.btnBackEditProfile.setOnClickListener {
            // Animate the view with slide-out effect before finishing the activity
            binding.root.animate()
                .translationX(1000f)
                .setDuration(800)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction { finish() }
                .start()
        }
    }
}