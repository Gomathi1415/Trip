package com.example.trip

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.trip.fragments.AlertFragment
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
import kotlinx.android.synthetic.main.list_of_available_trip_detail_card_view.*

class MapActivity : AppCompatActivity(),OnMapReadyCallback, AlertFragment.AlertCommunicator{

    private var mLocationPermissionsGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    lateinit var mGps: ImageView
    var currLat: Double = 0.0
    lateinit var marker : Marker
    var currLong: Double = 0.0
    var isNearBy: Boolean = false
    var gps_enabled: Boolean = false
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    lateinit var cityName: String
    lateinit var type: String
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var position: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        mGps = findViewById<ImageView>(R.id.ic_gps) as ImageView
        val intent: Intent = intent

        if (intent.hasExtra("nearByType")) {
            type = intent.getStringExtra("nearByType")
            isNearBy = true
            cityName = " "

        } else {
            cityName = intent.getStringExtra("cityName")
            type = intent.getStringExtra("type")
        }

        getLocationPermission()

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (mLocationPermissionsGranted) {
            getDeviceLocation()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            mMap.isMyLocationEnabled = true

            mMap.uiSettings.isMyLocationButtonEnabled = false


        }
    }


    private fun geoLocate() {
        var index = 0

        for (latlong in TripDetails.Supplier.tripDetails) {

            if (type == "Explore Button" && cityName.contains(latlong.city)) {
                moveCamera(
                    LatLng(latlong.latitude.toDouble(), latlong.longitude.toDouble()),
                    10f,
                    latlong.tripName,
                    index, latlong.type
                )
            } else if (type == latlong.type && cityName.contains(latlong.city)) {
                moveCamera(
                    LatLng(latlong.latitude.toDouble(), latlong.longitude.toDouble()),
                    10f,
                    latlong.tripName,
                    index, latlong.type
                )


            }
//
            index++
        }

    }

    fun getDeviceLocation() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try {
            if (mLocationPermissionsGranted) {
                var location = fusedLocationProviderClient.lastLocation.addOnCompleteListener(object :
                    OnCompleteListener<Location> {
                    override fun onComplete(task: Task<Location>) {
                        if (task.isSuccessful && task.result != null && isLocationEnabled()) {

                            val currentLocation = task.result as Location
                            currLat = currentLocation.latitude
                            currLong = currentLocation.longitude
                            moveCamera(
                                LatLng(currentLocation.latitude, currentLocation.longitude),
                                15f,
                                "My Location",
                                -1, " "
                            )
                            if (isNearBy != true) {
                                geoLocate()
                            } else {
                                nearBy()
                            }

                        } else {
                            if (isLocationEnabled()) {
                                getDeviceLocation()
                            } else {
                                val manager = supportFragmentManager
                                val dialog= AlertFragment()
//
                                dialog.show(manager, "customDialog")
                            }
                        }
                    }

                })
            }
        } catch (e: SecurityException) {
        }

        mGps.setOnClickListener {
            moveCamera(LatLng(currLat, currLong), 10f, "My Location", -1, " ")

        }
    }

    fun moveCamera(latLng: LatLng, zoom: Float, title: String, index: Int, type: String) {
        val bitmapdraw: BitmapDrawable
        if (type == "Hotel") {
            bitmapdraw = getResources().getDrawable(R.drawable.hotel_pin) as BitmapDrawable
        } else if (type == "Restaurant") {
            bitmapdraw = getResources().getDrawable(R.drawable.restaurant_pin) as BitmapDrawable
        } else {
            bitmapdraw = getResources().getDrawable(R.drawable.trip_pin) as BitmapDrawable
        }
        val b: Bitmap = bitmapdraw.getBitmap()
        val smallMarker: Bitmap = Bitmap.createScaledBitmap(b, 100, 100, false)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
        if (!title.equals("My Location")) {

            val options: MarkerOptions = MarkerOptions().position(latLng).title(title).snippet(index.toString())
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
         marker =  mMap.addMarker(options)

        }
        mMap.setInfoWindowAdapter( MyInfoWindowAdapter());

        mMap.setOnInfoWindowClickListener(OnInfoWindowClickListener {
             position = it.snippet
            val intent = Intent(this, ListOfAvailableTripDetailActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("cityName",cityName)
            startActivity(intent)
        })

    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapActivity)
    }

    private fun getLocationPermission() {
        val permissions =
            arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    COURSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mLocationPermissionsGranted = true
                initMap()

            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

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
        val latitude: Double = Math.toRadians(currLat)
        val longitude = Math.toRadians(currLong)
        var distance: Double
        var index = 0


        for (latlong in TripDetails.Supplier.tripDetails) {

            val radius= 6371.0

            val cityLongitude: Double = Math.toRadians(latlong.longitude.toDouble())
            val cityLatitude: Double = Math.toRadians(latlong.latitude.toDouble())
            val lon: Double = longitude - cityLongitude
            val lat: Double = latitude - cityLatitude
            distance = radius * (2 * Math.asin(
                Math.sqrt(
                    Math.pow(Math.sin(lat / 2), 2.0) + Math.cos(cityLatitude) * Math.cos(latitude) * Math.pow(
                        Math.sin(
                            lon / 2
                        ), 2.0
                    )
                )
            ))

            if (distance <= 100 && type == latlong.type) {
                moveCamera(
                    LatLng(latlong.latitude.toDouble(), latlong.longitude.toDouble()),
                    10f,
                    latlong.tripName,
                    index, latlong.type
                )

            }
            index++
        }


    }

    fun isLocationEnabled(): Boolean {

        val lm: LocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }

        return gps_enabled

    }


    override fun onDialogMessege(message: Boolean) {
        if (message) {
            startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1)

        } else {
            onBackPressed()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getDeviceLocation()
        super.onActivityResult(requestCode, resultCode, data)
    }

    internal inner class MyInfoWindowAdapter : GoogleMap.InfoWindowAdapter {

        private val myContentsView: View

        init {

            myContentsView = layoutInflater.inflate(R.layout.list_of_available_trip_detail_card_view, null)

        }

        override fun getInfoWindow(marker: Marker): View? {
            return null
        }

        override fun getInfoContents(marker: Marker): View? {
            position = marker.snippet
            val place = TripDetails.Supplier.tripDetails[position.toInt()]
            val typeImage : ImageView = myContentsView.findViewById(R.id.typeImage) as ImageView
            val name : TextView = myContentsView.findViewById(R.id.name) as TextView
            val address : TextView = myContentsView.findViewById(R.id.address) as TextView
            val review : TextView = myContentsView.findViewById(R.id.review) as TextView
            val description : TextView = myContentsView.findViewById(R.id.description) as TextView
            val rupee : ImageView = myContentsView.findViewById(R.id.rupee) as ImageView
            val price : TextView = myContentsView.findViewById(R.id.price) as TextView

            typeImage.setImageResource(place.image)
            name.setText(place.tripName)
            address.setText(place.address)

            review.setText(place.reviews.toString())
            description.setText(place.description)
            if(place.type=="Hotel") {
                rupee.maxHeight=24
                rupee.maxWidth=24
                price.textSize= 20F
                rupee.visibility=View.VISIBLE
                price.visibility = View.VISIBLE
                price.text = place.price
                rupee.setImageResource(R.drawable.rupee)
            }
            else
            {
                rupee.visibility=View.GONE
                price.visibility=View.GONE
            }

            return myContentsView
        }
    }

}