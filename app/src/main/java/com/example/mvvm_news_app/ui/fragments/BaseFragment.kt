package com.example.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvm_news_app.databinding.BaseFragmentBinding
import com.example.mvvm_news_app.ui.NewsActivity

open class BaseFragment:Fragment() {
    private val binding by lazy { BaseFragmentBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }
    fun navigationVisibility(isVisible :Boolean){

        (activity as NewsActivity).navigationVisibility(isVisible)

    }

    fun navigateTo(id: Int ) {
        findNavController().navigate(id)
    }

}