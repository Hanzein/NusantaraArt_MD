package com.capstone.bangkit.NusArt.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.capstone.bangkit.NusArt.R
import com.capstone.bangkit.NusArt.data.ResultState
import com.capstone.bangkit.NusArt.databinding.ActivitySignupBinding
import com.capstone.bangkit.NusArt.preference_manager.LanguageManager
import com.capstone.bangkit.NusArt.view.ViewModelFactory
import com.capstone.bangkit.NusArt.view.login.LoginActivity
import com.capstone.bangkit.NusArt.view.welcome.WelcomeActivity
import java.util.Locale

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
        setupLanguage()
        setupKeyboardClosing()
    }

    private fun setupLanguage() {
        val language = LanguageManager.getLanguage(this)

        val config = resources.configuration
        config.setLocale(Locale(language))
        resources.updateConfiguration(config, resources.displayMetrics)
    }


    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnBackSignup.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }

        binding.tvSignupMsgbottom2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.signupButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.registerUser(email, password).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            showLoading(true)
                        }

                        is ResultState.Success -> {
                            showToast(result.data.errorMessage.toString())
                            showLoading(false)
                            AlertDialog.Builder(this).apply {
                                setTitle(getString(R.string.success))
                                setMessage(getString(R.string.register_successful))
                                setPositiveButton(getString(R.string.next)) { _, _ ->
                                    val intent = Intent(context, WelcomeActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(intent)
                                    finish()
                                }
                                create()
                                show()
                            }
                        }

                        is ResultState.Error -> {
                            showToast(result.error)
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val messageTitle = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(100)


        AnimatorSet().apply {
            playSequentially(
                title,
                messageTitle,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 1000
        }.start()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupKeyboardClosing() {
        val rootLayout = findViewById<View>(android.R.id.content)
        rootLayout.setOnTouchListener { _, _ ->
            currentFocus?.let { focusedView ->
                val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(focusedView.windowToken, 0)
                focusedView.clearFocus()
            }
            false
        }
    }
}