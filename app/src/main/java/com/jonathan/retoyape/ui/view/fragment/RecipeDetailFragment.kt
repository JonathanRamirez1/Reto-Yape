package com.jonathan.retoyape.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import coil.imageLoader
import coil.request.ImageRequest
import com.jonathan.retoyape.R
import com.jonathan.retoyape.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var navController: NavController

    private var locationName: String? = null
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRecipeFragment(view)
        launchRecipeMapFragment(view)
        setContent()
    }

    private fun launchRecipeFragment(view: View) {
        navController = Navigation.findNavController(view)
        binding.imageViewBack.setOnClickListener {
            navController.navigate(R.id.recipeFragment)
        }
    }

    private fun launchRecipeMapFragment(view: View) {
        navController = Navigation.findNavController(view)
        binding.floatingButtonLocation.setOnClickListener {
            navController.navigate(
                R.id.recipeMapFragment,
                bundleOf(
                    "locationName" to locationName,
                    "latitude" to latitude,
                    "longitude" to longitude,
                )
            )
        }
    }

    private fun setContent() {
        arguments?.let { bundle ->
            locationName = bundle.getString("locationName")
            latitude = bundle.getDouble("latitude")
            longitude = bundle.getDouble("longitude")
            binding.apply {
                val imageRequest = context?.let {
                    ImageRequest.Builder(it)
                        .data(bundle.getString("image"))
                        .crossfade(true)
                        .size(1280, 720)
                        .target(
                            onStart = {
                                imageViewRecipe.setImageResource(R.drawable.ic_access_time)
                            },
                            onSuccess = { avatar ->
                                imageViewRecipe.scaleType = ImageView.ScaleType.CENTER_CROP
                                imageViewRecipe.setImageDrawable(avatar)
                            },
                            onError = {
                                imageViewRecipe.setImageResource(R.drawable.ic_load_error)
                            }
                        )
                        .build()
                }
                if (imageRequest != null) {
                    context?.imageLoader?.enqueue(imageRequest)
                }
                materialTextViewName.text = bundle.getString("name")
                materialTextViewDescription.text = bundle.getString("description")
            }
        }
    }
}