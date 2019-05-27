package com.example.trip.models

import java.io.Serializable

class SpotDetails : Serializable{

        var spotName: String? = null
        var spotDescription: String? = null
        var spotPrice = 0.0
        var spotAddress: String? = null
       lateinit var cityName: String
       lateinit var type: String
        var kmLimit: Int = 0
        var position: Int = 0


}