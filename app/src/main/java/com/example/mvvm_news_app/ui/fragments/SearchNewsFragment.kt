package com.example.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_news_app.adapters.NewsAdapter
import com.example.mvvm_news_app.databinding.FragmentSearchNewsBinding
import com.example.mvvm_news_app.ui.NewsActivity
import com.example.mvvm_news_app.ui.NewsViewModel
import com.example.mvvm_news_app.util.Resource

class SearchNewsFragment:BaseFragment() {
    private val binding by lazy {FragmentSearchNewsBinding.inflate(layoutInflater) }
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter :NewsAdapter
    val TAG = "SearchNewsFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewmodel
        setupRecycleView()

        viewModel.searchNews.observe(viewLifecycleOwner, Observer {
                response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newRespone ->
                        newsAdapter.differ.submitList(newRespone.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG ,"An error occured : $message")
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter =newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    }
