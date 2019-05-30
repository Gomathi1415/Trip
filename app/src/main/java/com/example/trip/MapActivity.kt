package com.example.trip

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.ImageView
import com.example.trip.models.TripDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.tasks.Task
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener




class MapActivity : AppCompatActivity() ,OnMapReadyCallback{

    private var mLocationPermissionsGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    lateinit var mGps :ImageView
    var currLat:Double = 0.0
    var currLong : Double = 0.0
    var isNearBy : Boolean = false
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    lateinit var cityName:String
    lateinit var type : String

    lateinit var fusedLocationProviderClient : FusedLocationProviderClient


    override fun onCreate( savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)


        getLocationPermission()
        mGps= findViewById(R.id.ic_gps) as ImageView
        var intent : Intent = intent
        if(intent.hasExtra("nearByType"))
        {
            type=intent.getStringExtra("nearByType")
            isNearBy=true
            cityName=" "

        }
        else {
            cityName = intent.getStringExtra("cityName")
            type = intent.getStringExtra("type")
        }



    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        if(mLocationPermissionsGranted)
        {
            getDeviceLocation()
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return
            }
            mMap.isMyLocationEnabled = true

            mMap.uiSettings.isMyLocationButtonEnabled = false


        }
    }


    private fun geoLocate() {
        var index =0

        for (latlong in TripDetails.Supplier.tripDetails) {

            if (type == "Explore Button" && cityName.contains(latlong.city)) {
                moveCamera(LatLng(latlong.latitude.toDouble(), latlong.longitude.toDouble()), 10f, latlong.tripName,index)

            } else if (type == latlong.type && cityName.contains(latlong.city)) {
                moveCamera(LatLng(latlong.latitude.toDouble(), latlong.longitude.toDouble()), 10f, latlong.tripName,index)


            }
//
          index++
        }

    }

    fun getDeviceLocation()
    {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try
        {
            if(mLocationPermissionsGranted)
            {
                var location = fusedLocationProviderClient.lastLocation.addOnCompleteListener(object :OnCompleteListener<Location>
                {
                    override fun onComplete(task: Task<Location>)
                    {
                        if (task.isSuccessful)
                        {
                            val currentLocation = task.result as Location
                            currLat=currentLocation.latitude
                            currLong=currentLocation.longitude
                            moveCamera(LatLng(currentLocation.latitude,currentLocation.longitude),15f,"My Location",-1)
                            if(isNearBy!=true) {
                                geoLocate()
                            }
                            else
                            {
                                 nearBy()
                            }

                        }
                    }

                })
            }
        }catch(e : SecurityException ){}
        mGps.setOnClickListener {
            moveCamera(LatLng(currLat,currLong),10f,"My Location",-1)

        }
    }
    fun moveCamera( latLng: LatLng,zoom : Float,title :String,index  :Int)
    {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))
        if(!title.equals("My Location"))
        {

            var options : MarkerOptions = MarkerOptions().position(latLng).title(title).snippet(index.toString())
            mMap.addMarker(options)
        }
        mMap.setOnInfoWindowClickListener(OnInfoWindowClickListener {
            var position  :String = it.snippet


            var intent :Intent = Intent(this,ListOfAvailableTripDetailActivity::class.java)
            intent.putExtra("position",position)

            startActivity(intent)
        })


    }
    private fun initMap() {Log.d("tag", "initMap: initializing map")
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapActivity)
    }

    private fun getLocationPermission() {
        val permissions =
            arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (ContextCompat.checkSelfPermission(this.applicationContext, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this.applicationContext, COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true
                initMap()

            }
            else
            {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
        else
        {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {

        mLocationPermissionsGranted = false

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    for (i in grantResults.indices) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false
                            return
                        }
                    }
                    mLocationPermissionsGranted = true
                    //initialize our map
                    initMap()
                }
            }
        }
    }
    fun nearBy() {
        var latitude : Double = Math.toRadians(currLat)
        var longitude = Math.toRadians(currLong)
        var distance:  Double = 0.0
        var index =0

        for (latlong in TripDetails.Supplier.tripDetails) {

            var radius :Double = 6371.0

            var cityLongitute :Double = Math.toRadians(latlong.longitude.toDouble());
           var cityLatitude :Double = Math.toRadians(latlong.latitude.toDouble());
             var lon :Double = longitude - cityLongitute;
          var lat :Double = latitude - cityLatitude;
          distance  = radius * (2 * Math.asin(Math.sqrt(Math.pow(Math.sin(lat/2),2.0) + Math.cos(cityLatitude) * Math.cos(latitude) * Math.pow(Math.sin(lon/2),2.0))));

            if(distance<=100 && type==latlong.type)
            {
                moveCamera(LatLng(latlong.latitude.toDouble(),latlong.longitude.toDouble()),10f,latlong.tripName,index)

            }
            index++
        }

    }
}

