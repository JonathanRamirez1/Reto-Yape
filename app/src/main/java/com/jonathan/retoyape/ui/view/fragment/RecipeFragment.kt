package com.jonathan.retoyape.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonathan.retoyape.databinding.FragmentRecipeBinding
import com.jonathan.retoyape.ui.view.adapter.RecipeAdapter

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }
}