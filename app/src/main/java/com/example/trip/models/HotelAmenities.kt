package com.example.trip.models



data class HotelAmenities(var hotelName: String,var roomService : Boolean,var freeParking :Boolean,var restaurant :Boolean,var freeWiFi:Boolean,var breakfastIncluded : Boolean,var pool : Boolean,var bar : Boolean,var gym : Boolean,var spa : Boolean,var starType  :Int) {
    object Supplier {
        val hotelAmenities = mutableListOf<HotelAmenities>(
            HotelAmenities("The Oberoi",true,true,true,true,false,false,true,true,true,5),
            HotelAmenities("Palm Meadows Club",true,true,true,true,true,true,true,true,true,4),
            HotelAmenities("Radisson",true,true,true,true,true,true,true,true,true,5),
            HotelAmenities("Savera Hotel",true,true,true,true,true,true,true,true,true,4),
            HotelAmenities("GreenPark",true,true,true,true,true,true,true,true,true,4),
            HotelAmenities("Parakkat Nature Hotels & Resorts",true,true,true,true,true,false,false,false,true,4),
            HotelAmenities("Hotel Great Jubilee",true,true,true,true,true,false,false,false,true,3),
            HotelAmenities("TGI Star Holidays Yercaud",true,true,true,false,true,true,false,false,true,3),
            HotelAmenities("Sinclairs Retreat",true,true,true,true,true,false,true,true,false,3),
            HotelAmenities("Amaana Plantation Resort",true,true,true,true,true,false,false,false,false,0),
            HotelAmenities("Swiss County",true,true,true,true,true,false,false,false,false,3),
            HotelAmenities("Sterling Kodai Valley",true,true,false,true,true,false,false,false,false,3)

        )
    }
}
