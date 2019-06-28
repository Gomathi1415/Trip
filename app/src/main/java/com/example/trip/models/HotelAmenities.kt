package com.example.trip.models


class HotelAmenities {
    lateinit var hotelName: String
    lateinit var roomService: String
    lateinit var freeParking: String
    lateinit var restaurant: String
    lateinit var freeWiFi: String
    lateinit var breakfastIncluded: String
    lateinit var pool: String
    lateinit var bar: String
    lateinit var gym: String
    lateinit var spa: String
    lateinit var starType: String


    constructor(
        hotelName: String,
        roomService: String,
        freeParking: String,
        restaurant: String,
        freeWiFi: String,
        breakfastIncluded: String,
        pool: String,
        bar: String,
        gym: String,
        spa: String,
        starType: String

    ) {
        this.hotelName = hotelName
        this.roomService = roomService

        this.freeParking = freeParking
        this.restaurant = restaurant
        this.freeWiFi = freeWiFi
        this.breakfastIncluded = breakfastIncluded
        this.pool = pool
        this.bar = bar
        this.gym = gym
        this.spa = spa
        this.starType = starType
    }

    constructor() {}
    object Supplier {
        val hotelAmenities = mutableListOf<HotelAmenities>()
    }
}
