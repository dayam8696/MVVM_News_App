package com.example.mvvm_news_app.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mvvm_news_app.R
import com.example.mvvm_news_app.databinding.ActivityNewsBinding
import com.example.mvvm_news_app.db.ArticleDatabase
import com.example.mvvm_news_app.repository.NewsRepository

class NewsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNewsBinding.inflate(layoutInflater) }
    lateinit var viewmodel :NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val newsrepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsrepository)
        viewmodel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val bottomNav = binding.bottomNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.NavHost) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
    }

    fun navigationVisibility(isVisible: Boolean) {
        binding.apply {
            bottomNav.clearAnimation()
            if (isVisible) {
                bottomNav.animate()
                    .translationY(0.0f)
                    .alpha(1.0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator) {
                            super.onAnimationStart(animation)
                            bottomNav.visibility = View.VISIBLE
                        }
                    })
            } else {
                bottomNav.animate()
                    .translationY(bottomNav.height.toFloat())
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            bottomNav.visibility = View.GONE
                        }
                    })
            }
        }
    }
    }
