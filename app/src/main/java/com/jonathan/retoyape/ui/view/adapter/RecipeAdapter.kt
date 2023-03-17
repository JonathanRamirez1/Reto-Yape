package com.jonathan.retoyape.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.jonathan.retoyape.R
import com.jonathan.retoyape.databinding.ItemRecipesBinding
import com.jonathan.retoyape.domain.model.Recipe

class RecipeAdapter(val itemRecipes: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

     lateinit var context: Context
     lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(
            ItemRecipesBinding.inflate(
                LayoutInflater.from(context)
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.bind(itemRecipes[position], context)
        holder.itemView.setOnClickListener { view ->
            val result = itemRecipes[position]

            navController = Navigation.findNavController(view)
            navController.navigate(
                R.id.recipeDetailFragment,
                bundleOf(
                    "image" to result.image,
                    "name" to result.name,
                    "description" to result.description,
                    "locationName" to result.locationName,
                    "latitude" to result.latitude,
                    "longitude" to result.longitude,
                )
            )
        }
    }

    override fun getItemCount(): Int = itemRecipes.size

    fun submitList(itemList: ArrayList<Recipe>) {
        this.itemRecipes.clear()
        this.itemRecipes.addAll(itemList)
        notifyItemInserted(itemRecipes.size - 1)
    }

     class RecipeViewHolder(private val binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipes: Recipe, context: Context) {
            val imageRequest = ImageRequest.Builder(context)
                .data(recipes.image)
                .crossfade(true)
                .size(1280, 720)
                .target(
                    onStart = {
                        binding.imageViewRecipes.setImageResource(R.drawable.ic_access_time)
                    },
                    onSuccess = { avatar ->
                        binding.progressBar.visibility = View.GONE
                        binding.imageViewRecipes.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.imageViewRecipes.setImageDrawable(avatar)
                    },
                    onError = {
                        binding.progressBar.visibility = View.GONE
                        binding.imageViewRecipes.setImageResource(R.drawable.ic_load_error)
                    }
                )
                .build()
            context.imageLoader.enqueue(imageRequest)
            binding.textViewName.text = recipes.name
        }
    }
}