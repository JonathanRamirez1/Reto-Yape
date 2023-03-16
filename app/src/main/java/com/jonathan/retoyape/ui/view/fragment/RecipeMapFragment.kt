package com.jonathan.retoyape.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jonathan.retoyape.R
import com.jonathan.retoyape.databinding.FragmentRecipeMapBinding

class RecipeMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMaps: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       val binding = FragmentRecipeMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMaps = googleMap
        arguments?.let { bundle ->
            val latitude = bundle.getDouble("latitude")
            val longitude = bundle.getDouble("longitude")
            val locationName = bundle.getString("locationName")
            val location = LatLng(latitude, longitude)
            googleMaps.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(locationName)
            )
            googleMaps.moveCamera(CameraUpdateFactory.newLatLng(location))
        }
    }
}