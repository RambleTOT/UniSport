package com.example.unisportinverse.presentation.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.FragmentActivity
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.example.unisportinverse.presentation.adapters.BottomSheetGround
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment(){

    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placeMarkMapObject: PlacemarkMapObject
    private var binding: FragmentMapBinding? = null
    private var zoomValue: Float = 16.5f
    private lateinit var locationManager: LocationManager
    private lateinit var startLocation: Point
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }


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
        init()
        getMyLocation()
    }

    private fun init(){
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun setMarkerInStartLocation() {
        val marker = createBitmapFromVector(R.drawable.icon_map_mark)
        mapObjectCollection = binding!!.mapView.map.mapObjects // Инициализируем коллекцию различных объектов на карте
        placeMarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromBitmap(marker))
        placeMarkMapObject.addTapListener(mapObjectTapListener)
    }

    private fun moveToStartLocation() {
        Log.d("MyLog", "location")
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

    private val mapObjectTapListener = MapObjectTapListener { mapObject, point ->
        showBottomSheet()
        true
    }

    private fun showBottomSheet(){
        val bottomSheet = BottomSheetGround()
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        fragmentManager.let {
            bottomSheet.show(it, BottomSheetGround.TAG)
        }
    }

    private fun getMyLocation(){
        if (checkPermissions()){
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermission()
                return
            }
            fusedLocationProviderClient.lastLocation.addOnCompleteListener(requireActivity()){
                task ->
                val location: Location? = task.result
                startLocation = Point(location!!.latitude, location!!.longitude, )
                moveToStartLocation()
                setMarkerInStartLocation()
            }
        }else{
            requestPermission()
        }
    }


    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun checkPermissions(): Boolean{
        if (ActivityCompat.checkSelfPermission(requireActivity(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            return false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getMyLocation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding!!.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding!!.mapView.onStop()
    }

}