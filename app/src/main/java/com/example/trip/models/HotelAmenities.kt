package com.example.trip.models



data class HotelAmenities(var hotelName: String,var roomService : Boolean,var freeParking :Boolean,var restaurant :Boolean,var freeWiFi:Boolean,var breakfastIncluded : Boolean,var pool : Boolean,var bar : Boolean,var gym : Boolean,var spa : Boolean) {
    object Supplier {
        val hotelAmenities = mutableListOf<HotelAmenities>(
            HotelAmenities("The Oberoi",true,true,true,true,false,false,true,true,true),
            HotelAmenities("Palm Meadows Club",true,true,true,true,true,true,true,true,true),
            HotelAmenities("Radisson",true,true,true,true,true,true,true,true,true),
            HotelAmenities("Savera Hotel",true,true,true,true,true,true,true,true,true),
            HotelAmenities("GreenPark",true,true,true,true,true,true,true,true,true),
            HotelAmenities("Parakkat Nature Hotels & Resorts",true,true,true,true,true,false,false,false,true),
            HotelAmenities("Hotel Great Jubilee",true,true,true,true,true,false,false,false,true),
            HotelAmenities("TGI Star Holidays Yercaud",true,true,true,false,true,true,false,false,true),
            HotelAmenities("Sinclairs Retreat",true,true,true,true,true,false,true,true,false),
            HotelAmenities("Amaana Plantation Resort",true,true,true,true,true,false,false,false,false),
            HotelAmenities("Swiss County",true,true,true,true,true,false,false,false,false)

            )
    }
}
