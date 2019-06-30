package com.example.trip


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.trip.fragments.DisplayDescriptionFragment
import com.example.trip.fragments.DisplaysCityNameFragment
import com.example.trip.models.TripDetails

class DisplayCityNameActivity : AppCompatActivity(),Communicator,MapViewAdapterListener,DisplayFullImageListener {

    lateinit var type : String
    lateinit var displayDescriptionFragment: DisplayDescriptionFragment
    lateinit var fragmentTransaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.displays_city_name_activity)
        displayDescriptionFragment = DisplayDescriptionFragment()

        val intent : Intent = intent
        if(intent.hasExtra("position"))
        {
            var position = intent.getStringExtra("position")
            var hasIndex = intent.getIntExtra("hasIndex",0)
//            getSupportActionBar()!!.title = TripDetails.Supplier.tripDetails[position.toInt()].tripName

            displayDescriptionFragment.positionNumber(position)

            fragmentTransaction = supportFragmentManager.beginTransaction()

            if (hasIndex==1) {
                fragmentTransaction.replace(R.id.fragmentScreen, displayDescriptionFragment)
                    .commit()
            } else {
                fragmentTransaction.replace(R.id.fragmentScreen, displayDescriptionFragment)
                    .commit()

            }
        }
        else {
            type = intent.getStringExtra("type")
            val displaysCityNameFragment = DisplaysCityNameFragment()
            displaysCityNameFragment.changeData(type)
            setFragment(displaysCityNameFragment)
        }


    }
    private fun setFragment(fragment: Fragment): Boolean {
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen,fragment).commit()
        return  true
    }
    override fun response(string: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cityNameListener(cityName: String,type : String) {
        val intent =Intent(this, ListOfAvailableTripDetailActivity::class.java)
        intent.putExtra("cityName",cityName)
        intent.putExtra("type",type)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()

    }
    override fun openMapListener(string: String) {
        val intent  =Intent(this, MapActivity::class.java)
        intent.putExtra("nearByType",string)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun openImage(position: String,tripPosition:String) {
        val intent : Intent = Intent(this,FullScreenActivity::class.java)
        intent.putExtra("position",position)
        intent.putExtra("tripPosition",tripPosition)
        startActivity(intent)
    }

}
