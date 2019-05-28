package com.example.trip


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.trip.fragments.ExploreFragment

class MainActivity : AppCompatActivity() ,Communicator{

    lateinit var exploreFragment : ExploreFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        exploreFragment = ExploreFragment()
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentScreen,exploreFragment)
        fragmentTransaction.commit()

    }
    override fun response(type: String) {
        val intent : Intent = Intent(this,DisplayCityNameActivity::class.java)
        intent.putExtra("type",type)
        startActivity(intent)
    }
    override fun cityNameListener(cityName: String,type:String) {
        val intent :Intent =Intent(this, ListOfAvailableTripDetailActivity::class.java)
                intent.putExtra("cityName",cityName)
                intent.putExtra("type",type)
            startActivity(intent)
    }

    }




