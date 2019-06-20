package com.example.trip


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.Toolbar
import com.example.trip.fragments.FullViewFragment
import com.example.trip.models.TripDetails


class FullScreenActivity : AppCompatActivity() {
    var position : Int =0
    var tripPosition :Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_screen_activity)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        position = intent.getStringExtra("position").toInt()
        tripPosition =intent.getStringExtra("tripPosition").toInt()
        getSupportActionBar()!!.title = TripDetails.Supplier.tripDetails[tripPosition].tripName
        val  fullViewFragment = FullViewFragment()
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen,fullViewFragment)
        fragmentTransaction.commit()
        fullViewFragment.currPosition(position,tripPosition)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

