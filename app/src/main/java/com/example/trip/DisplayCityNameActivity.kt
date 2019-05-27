package com.example.trip


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.view.Window
import android.widget.Toast
import com.example.trip.fragments.DisplaysCityNameFragment
import com.example.trip.fragments.UserPreferenceSelectionFragment
import com.example.trip.models.ListOfTrendingPlaces
import com.example.trip.models.SpotDetails
import kotlinx.android.synthetic.main.displays_city_name_activity.*


class DisplayCityNameActivity : AppCompatActivity(),Communicator{


    lateinit var type : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.displays_city_name_activity)

        var intent : Intent = intent
            type = intent.getStringExtra("type")
            var displaysCityNameFragment = DisplaysCityNameFragment()
                displaysCityNameFragment.changeData(type)
                setFragment(displaysCityNameFragment)

}
    private fun setFragment(fragment: Fragment): Boolean {
        var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen,fragment).commit()
        return  true
    }
    override fun response(string: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cityNameListener(city: String,type : String) {
        val intent :Intent =Intent(this, ListOfAvailableTripDetailActivity::class.java)
        intent.putExtra("cityName",city)
        intent.putExtra("type",type)
        startActivity(intent)

    }
}
