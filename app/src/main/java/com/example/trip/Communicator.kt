package com.example.trip

import com.example.trip.models.SpotDetails

interface Communicator {

    fun response(string: String)
    fun cityNameListener(cityName :String,type:String)
}