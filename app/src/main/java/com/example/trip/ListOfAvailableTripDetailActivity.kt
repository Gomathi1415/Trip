package com.example.trip

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.trip.fragments.DisplayDescriptionFragment
import com.example.trip.fragments.UserPreferenceSelectionFragment
import com.example.trip.models.ListOfTrendingPlaces
import com.example.trip.models.SpotDetails




class ListOfAvailableTripDetailActivity : AppCompatActivity(),MapViewAdapterListener,RecyclerAdapterListener {

    lateinit var cityName: String
    lateinit var type: String
    lateinit var spotDetails: SpotDetails
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var displayDescriptionFragment: DisplayDescriptionFragment
    lateinit var userPreferenceSelectionFragment: UserPreferenceSelectionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_list_of_trip_detail_activity)

        spotDetails = SpotDetails()
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        userPreferenceSelectionFragment = UserPreferenceSelectionFragment()
        displayDescriptionFragment = DisplayDescriptionFragment()
        val intent : Intent = intent
        cityName = intent.getStringExtra("cityName")
        type = intent.getStringExtra("type")
        getSupportActionBar()!!.title = cityName
        spotDetails.cityName = cityName
        spotDetails.type = type
        var flag = 0
        for (city in ListOfTrendingPlaces.Supplier.recentSearches) {
            if (city.title == cityName) {
                flag = 1
            }
        }
        if (flag == 0) {
            ListOfTrendingPlaces.Supplier.recentSearches.add(0, ListOfTrendingPlaces(cityName))
        }
        userPreferenceSelectionFragment.changeData(spotDetails)
        setFragment(userPreferenceSelectionFragment)

    }

    private fun setFragment(fragment: Fragment): Boolean {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listfragmentScreen, fragment).commit()
        return true
    }

    override fun openMapListener() {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("cityName",cityName)
        intent.putExtra("type",type)
        startActivity(intent)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onTrendingPlaceViewClicked(position: String) {
        displayDescriptionFragment.positionNumber(position)
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listfragmentScreen, displayDescriptionFragment).addToBackStack(null).commit()

    }


}
