package com.example.unisportinverse.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
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
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placeMarkMapObject: PlacemarkMapObject
    private var binding: FragmentMapBinding? = null
    private var zoomValue: Float = 16.5f
    private lateinit var locationManager: LocationManager
    private val startLocation = Point(56.867573, 60.655382)

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
        moveToStartLocation()
        setMarkerInStartLocation()
    }

    private fun setMarkerInStartLocation() {
        val marker = createBitmapFromVector(R.drawable.icon_map_mark)
        mapObjectCollection = binding!!.mapView.map.mapObjects // Инициализируем коллекцию различных объектов на карте
        placeMarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromBitmap(marker))
    }

    private fun moveToStartLocation() {
        binding!!.mapView.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
        )
    }

    private fun createBitmapFromVector(art: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(requireActivity(), art) ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        ) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
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