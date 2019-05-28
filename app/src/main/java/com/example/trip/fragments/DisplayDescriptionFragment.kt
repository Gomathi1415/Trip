package com.example.trip.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.trip.R
import com.example.trip.models.TripDetails
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.display_description_fragment.*


class DisplayDescriptionFragment : Fragment(),OnMapReadyCallback {
    lateinit var mGoogleMap : GoogleMap
    lateinit var mMapView: MapView
    lateinit var mView : View
    lateinit var lat:String
    lateinit var tripName :String
    lateinit var long :String

    var position : Int =0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         mView = inflater.inflate(R.layout.display_description_fragment, container, false)

return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var place = TripDetails.Supplier.tripDetails[position]
            displayTypeImage.setImageResource(R.drawable.hotel)
        nameOfTheTrip.setText(place.tripName)
        finalDecription.setText(place.description)
        finalAddress.setText(place.address)
          if(place.type=="Hotel") {
              price.setText("Price for per night -  ${place.price}/-")
          }
        tripName=place.tripName
        lat = place.latitude
        long = place.longitude
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mMapView=mView.findViewById(R.id.mapView)
        if(mMapView != null)
        {
            mMapView.onCreate(null)
            mMapView.onResume()
            mMapView.getMapAsync(this)
        }
    }




    fun positionNumber(position: String) {
        this.position=position.toInt()

    }
    override fun onMapReady(googleMap: GoogleMap) {
          MapsInitializer.initialize(context)
        mGoogleMap= googleMap
        googleMap.mapType=GoogleMap.MAP_TYPE_NORMAL
       googleMap.addMarker(MarkerOptions().position(LatLng(lat.toDouble(),long.toDouble())).title((tripName)))
        var mapPlace : CameraPosition = CameraPosition.builder().target(LatLng(lat.toDouble(),long.toDouble())).zoom(16F).bearing(0F).tilt(45F).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(mapPlace))

    }


}
