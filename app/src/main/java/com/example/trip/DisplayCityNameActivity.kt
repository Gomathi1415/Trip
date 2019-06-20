package com.example.trip


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.trip.fragments.DisplaysCityNameFragment


class DisplayCityNameActivity : AppCompatActivity(),Communicator,MapViewAdapterListener{
    override fun openMapListener(string: String) {
        val intent  =Intent(this, MapActivity::class.java)
        intent.putExtra("nearByType",string)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }


    lateinit var type : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.displays_city_name_activity)

        val intent : Intent = intent
            type = intent.getStringExtra("type")
        val displaysCityNameFragment = DisplaysCityNameFragment()
                displaysCityNameFragment.changeData(type)
                setFragment(displaysCityNameFragment)


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
}
