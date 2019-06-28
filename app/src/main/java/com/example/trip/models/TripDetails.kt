package com.example.trip.models

import com.example.trip.R


class TripDetails {

    var id: Int = 0
    lateinit var type: String
    lateinit var tripName: String
    lateinit var description: String
    lateinit var price: String
    lateinit var address: String
    lateinit var city: String
    lateinit var latitude: String
    lateinit var longitude: String
    lateinit var phone_no: String
    lateinit var reviews: String
    lateinit var website: String
    lateinit var diet: String
    lateinit var no_of_people_reviewed :String

    constructor(
        id: Int,
        type: String,
        tripName: String,
        description: String,
        price: String,
        address: String,
        city: String,
        latitude: String,
        longitude: String,
        phone_no: String,
        reviews: String,
        website: String,
        diet: String,
        no_of_people_reviewed : String
    ) {
        this.id = id
        this.type = type

        this.tripName = tripName
        this.description = description
        this.price = price
        this.address = address
        this.city = city
        this.latitude = latitude
        this.longitude = longitude
        this.no_of_people_reviewed=no_of_people_reviewed
        this.phone_no = phone_no
        this.reviews = reviews
        this.website = website
        this.diet = diet

    }

    constructor() {
    }

    object Supplier {
        val tripDetails = mutableListOf<TripDetails>()
    }
}
