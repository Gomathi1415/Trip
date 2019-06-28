package com.example.trip.DAO

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.trip.models.HotelAmenities
import database.Database

class AmenitiesDAO {

    var hotelName: String
    var roomService: String
    var freeParking: String
    var restaurant: String
    var freeWiFi: String
    var breakfastIncluded: String
    var pool: String
    var bar: String
    var gym: String
    var spa: String
    var table_name: String
    var starType: String
    var dropTable: String
    var createTable: String


    init {
        this.hotelName = "HOTEL_NAME"
        this.table_name = "amenities"
        this.roomService = "ROOM_SERVICE"
        this.freeParking = "FREE_PARKING"
        this.restaurant = "RESTAURANT"
        this.freeWiFi = "FREE_WIFI"
        this.breakfastIncluded = "BREAKFAST_INCLUDED"
        this.pool = "POOL"
        this.bar = "BAR"
        this.gym = "GYM"
        this.spa = "SPA"
        this.starType = "STAR_TYPE"
        this.dropTable = "drop table if exists $table_name"
        this.createTable =
            "create table $table_name($hotelName text,$roomService TEXT,$freeParking TEXT,$restaurant TEXT,$freeWiFi TEXT,$breakfastIncluded TEXT,$pool TEXT,$bar TEXT,$gym TEXT,$spa TEXT,$starType TEXT)"

    }

    fun getAmenities(context: Context): MutableList<HotelAmenities> {

        val dbHelpers: Database = Database(context, createTable, dropTable)
        val db: SQLiteDatabase = dbHelpers.readableDatabase
        val cursor: Cursor = db.rawQuery("select * from  ${table_name}", null)
        val result = mutableListOf<HotelAmenities>()
        if (cursor.moveToFirst()) {
            do {
                var tripDetails: HotelAmenities = HotelAmenities()
                tripDetails.hotelName = cursor.getString(cursor.getColumnIndex(hotelName))
                tripDetails.roomService = cursor.getString(cursor.getColumnIndex(roomService))
                tripDetails.freeParking = cursor.getString(cursor.getColumnIndex(freeParking))
                tripDetails.restaurant = cursor.getString(cursor.getColumnIndex(restaurant))
                tripDetails.freeWiFi = cursor.getString(cursor.getColumnIndex(freeWiFi))
                tripDetails.breakfastIncluded = cursor.getString(cursor.getColumnIndex(breakfastIncluded))
                tripDetails.pool = cursor.getString(cursor.getColumnIndex(pool))
                tripDetails.bar = cursor.getString(cursor.getColumnIndex(bar))
                tripDetails.gym = cursor.getString(cursor.getColumnIndex(gym))
                tripDetails.spa = cursor.getString(cursor.getColumnIndex(spa))
                tripDetails.starType = cursor.getString(cursor.getColumnIndex(starType))
                result.add(tripDetails)
            } while (cursor.moveToNext())
        }
        HotelAmenities.Supplier.hotelAmenities.addAll(result)
        Log.d("abcd",result.size.toString())
        cursor.close()
        db.close()
        dbHelpers.close()
        return result
    }


}
