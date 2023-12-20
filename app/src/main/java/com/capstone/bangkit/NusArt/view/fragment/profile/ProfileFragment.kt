package com.capstone.bangkit.NusArt.view.fragment.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.bangkit.NusArt.R
import com.capstone.bangkit.NusArt.data.pref.UserPreference
import com.capstone.bangkit.NusArt.data.pref.dataStore
import com.capstone.bangkit.NusArt.databinding.FragmentProfileBinding
import com.capstone.bangkit.NusArt.preference_manager.LanguageManager
import com.capstone.bangkit.NusArt.view.ViewModelFactory
import com.capstone.bangkit.NusArt.view.fragment.home.HomeViewModel
import com.capstone.bangkit.NusArt.view.language.LanguageActivity
import com.capstone.bangkit.NusArt.view.main.MainActivity
import com.capstone.bangkit.NusArt.view.welcome.WelcomeActivity
import java.util.Locale

class ProfileFragment : Fragment() {
    private lateinit var factory: ViewModelFactory
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profilepreference: UserPreference
//    private lateinit var viewModel : ProfileViewModel
    private val viewModel: ProfileViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        factory = ViewModelFactory.getInstance(requireActivity())

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        profilepreference = UserPreference.getInstance(requireContext().dataStore)

        setupLanguage()
//        setupView()
        setupAction()

        return binding.root
    }

    private fun setupLanguage() {
        val language = LanguageManager.getLanguage(requireActivity())
        val config = resources.configuration
        config.setLocale(Locale(language))
        resources.updateConfiguration(config, resources.displayMetrics)
    }

//    private fun setupView() {
//        val window = activity?.window
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window?.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window?.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener{
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            dialogLogout()
        }
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(requireActivity(), LanguageActivity::class.java))
        }
    }

    private fun setupProperty() {
             //profilepreference = UserPreference(requireActivity())
        }


    private fun dialogLogout() {
        AlertDialog.Builder(requireActivity()).apply {
            setTitle(R.string.logout_title)
            setMessage(R.string.logout_alert)
            setCancelable(false)
            setPositiveButton("Ok") { _, _ ->
                //profilepreferences.setToken(null)
                viewModel.logout()
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }
    }

