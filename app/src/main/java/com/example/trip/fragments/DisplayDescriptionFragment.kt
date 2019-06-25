package com.example.trip.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import com.example.trip.R
import com.example.trip.models.TripDetails
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.display_description_fragment.*
import android.content.Intent
import android.net.Uri
import android.view.*
import com.example.trip.DisplayFullImageListener
import com.example.trip.adapter.DescriptionViewPagerAdapter
import com.example.trip.models.HotelAmenities
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.explore_fragment.*
import android.text.style.UnderlineSpan
import android.text.SpannableString





class DisplayDescriptionFragment : Fragment(), OnMapReadyCallback, DisplayFullImageListener {

    lateinit var mGoogleMap: GoogleMap
    lateinit var mMapView: MapView
    lateinit var mView: View
    lateinit var lat: String
    lateinit var tripName: String
    lateinit var long: String
    lateinit var hotel: HotelAmenities
    var position: Int = 0
    var imagePosition: Int = 0
    lateinit var displayFullImageListener: DisplayFullImageListener
    lateinit var adapter: DescriptionViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.display_description_fragment, container, false)
        return mView
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {


        val place = TripDetails.Supplier.tripDetails[position]
        adapter = DescriptionViewPagerAdapter(context!!, place.imagess, this,1)
        descViewpager.adapter = adapter
        descIndicator.setupWithViewPager(descViewpager, true)

        descViewpager.setCurrentItem(0)
        imagePosition = 0

        setHasOptionsMenu(true)
        nameOfTheTrip.setText(place.tripName)
        finalDecription.setText(place.description)
        var content = SpannableString(place.address)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        finalAddress.setText(content)
        setHasOptionsMenu(false)
        content = SpannableString(place.websites)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        webSite.setText(content)

        if (place.type == "Hotel") {
            price.setText("Price per night -  ${place.price}/-")
        } else {
            price.visibility = View.GONE
        }

        if (place.phone_no != "") {
            content = SpannableString(place.phone_no)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            phoneNumber.setText(content)
            phoneIcon.setImageResource(R.drawable.phone)
            phoneNumber.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + place.phone_no.trim()))
                startActivity(intent)
            }
            phoneIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + place.phone_no.trim()))
                startActivity(intent)
            }
        } else {
            phoneIcon.visibility = View.GONE
            phoneNumber.visibility = View.GONE
        }
        if (place.type == "Hotel") {

            for (index in HotelAmenities.Supplier.hotelAmenities) {
                if (index.hotelName == place.tripName)
                    hotel = index

            }
            Damenities.setText("Amenities")

            if (hotel.roomService) {
                room_service.visibility = View.VISIBLE
            }
            if (hotel.bar) {
                bar.visibility = View.VISIBLE
            }
            if (hotel.freeParking) {
                parking.visibility = View.VISIBLE
            }
            if (hotel.spa) {
                spa.visibility = View.VISIBLE
            }
            if (hotel.gym) {
                gym.visibility = View.VISIBLE
            }
            if (hotel.breakfastIncluded) {
                breakfast.visibility = View.VISIBLE
            }
            if (hotel.freeWiFi) {
                wifi.visibility = View.VISIBLE
            }
            if (hotel.restaurant) {
                restAvailable.visibility = View.VISIBLE
            }
            if (hotel.pool) {
                pool.visibility = View.VISIBLE
            }
        }
        else
        {
            hotelAmenities.visibility = View.GONE
        }

        finalAddress.setOnClickListener {
            val intent = Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?&daddr=${place.latitude},${place.longitude}")
            )
            startActivity(intent)
        }
        adressIcon.setOnClickListener {
            val intent = Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?&daddr=${place.latitude},${place.longitude}")
            )
            startActivity(intent)

        }
        websiteIcon.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_VIEW)
            intent.setData(Uri.parse(place.websites))
            startActivity(intent)
        }
        webSite.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_VIEW)
            intent.setData(Uri.parse(place.websites))
            startActivity(intent)
        }

        tripName = place.tripName
        lat = place.latitude
        long = place.longitude
        val fab: FloatingActionButton = activity!!.findViewById<View>(R.id.mapButton) as FloatingActionButton
        fab.visibility = View.INVISIBLE
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.setTitle(place.tripName)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu!!.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mMapView = mView.findViewById(R.id.mapView)
        mMapView.onCreate(null)
        mMapView.onResume()
        mMapView.getMapAsync(this)
    }


    fun positionNumber(position: String) {
        this.position = position.toInt()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        mGoogleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.addMarker(MarkerOptions().position(LatLng(lat.toDouble(), long.toDouble())).title((tripName)))
        val mapPlace: CameraPosition =
            CameraPosition.builder().target(LatLng(lat.toDouble(), long.toDouble())).zoom(16F).bearing(0F).tilt(45F)
                .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(mapPlace))
        mGoogleMap.uiSettings.isZoomControlsEnabled = true
        mGoogleMap.uiSettings.isScrollGesturesEnabled = true
        mGoogleMap.uiSettings.isZoomGesturesEnabled = true
    }

    override fun openImage(imagePosition: String, tripPosition: String) {
        this.imagePosition = imagePosition.toInt()
        displayFullImageListener = activity as DisplayFullImageListener
        displayFullImageListener.openImage(imagePosition, position.toString())
    }

    override fun onResume() {
        super.onResume()
        if (imagePosition != 0) {
            descViewpager.setCurrentItem(imagePosition)
        } else {
            descViewpager.setCurrentItem(0)
        }
    }
}







