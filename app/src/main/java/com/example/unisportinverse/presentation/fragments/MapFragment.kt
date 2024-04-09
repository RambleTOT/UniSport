package com.example.unisportinverse.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.example.unisportinverse.databinding.FragmentRegistrationBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView

class MapFragment : Fragment() {

    private var binding: FragmentMapBinding? = null
    private var zoomValue: Float = 16.5f
    private lateinit var locationManager: LocationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationManager = MapKitFactory.getInstance().createLocationManager()
        if (checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        val filteringMode = FilteringMode.OFF

        locationManager.subscribeForLocationUpdates(0.0, 0, 0.0, false, filteringMode, object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                val latitude = location.position.latitude
                val longitude = location.position.longitude
               Log.d("MyLog", "Latitude: $latitude, Longitude: $longitude")
            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {
                TODO("Not yet implemented")
            }
        })
    }

//    private fun moveToMyLocation() {
//
//        val startLocation = getMyLocation()
//
//        binding!!.mapView.map.move(
//            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
//        )
//    }
//
//    private fun getMyLocation() : Point{
//
//    }

    override fun onStart() {
        super.onStart()
        binding!!.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding!!.mapView.onStop()
    }

}