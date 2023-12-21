package com.capstone.bangkit.NusArt.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.bangkit.NusArt.adapter.LoadingStateAdapter
import com.capstone.bangkit.NusArt.adapter.StoryAdapter
import com.capstone.bangkit.NusArt.data.ResultState
import com.capstone.bangkit.NusArt.data.pref.UserPreference
import com.capstone.bangkit.NusArt.data.pref.dataStore
import com.capstone.bangkit.NusArt.databinding.FragmentHomeBinding
import com.capstone.bangkit.NusArt.preference_manager.LanguageManager
import com.capstone.bangkit.NusArt.view.ViewModelFactory
import com.capstone.bangkit.NusArt.view.add.AddStoryActivity
import com.capstone.bangkit.NusArt.view.welcome.WelcomeActivity
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var factory: ViewModelFactory
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferences: UserPreference

    private val viewModel: HomeViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        factory = ViewModelFactory.getInstance(requireActivity())
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataStore = requireContext().dataStore
        preferences = UserPreference.getInstance(dataStore)

        val addButton = binding.fabAdd

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                requireActivity().finish()
            }
        }

       // setupView()
        setupProperty()
        setupLanguage()


        addButton.setOnClickListener {
            val moveIntent = Intent(requireActivity(), AddStoryActivity::class.java)
            startActivity(moveIntent)
        }
        setupAction()
    }
//
//    private fun setupView() {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            requireActivity().window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//    }

    private fun setupLanguage() {
        val language = LanguageManager.getLanguage(requireActivity())
        val config = resources.configuration
        config.setLocale(Locale(language))
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun getData() {
        val adapter = StoryAdapter()
        binding.rvItem.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvItem.adapter = adapter
        binding.rvItem.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.repo.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupProperty() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

    private fun setupAction() {
        viewModel.getStory().observe(requireActivity()) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        getData()
                        showLoading(false)
                    }

                    is ResultState.Error -> {
                        result.error
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.homeprogressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStory()
    }

}