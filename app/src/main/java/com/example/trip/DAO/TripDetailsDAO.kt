package com.example.myProject.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.trip.models.TripDetails
import database.Database


class TripDetailsDAO{


    var table_name :String
    var id: String
    var type: String
    var tripName: String
    var description: String
    var price: String
    var address: String
    var city: String
    var latitude: String
    var longitude: String
    var phone_no: String
    var reviews: String
    var website: String
    var diet: String
    var createTable :String
    var dropTable:String
    var no_of_people_reviwed  :String

    init {
        this.id = "ID"
        this.table_name = "tripDetails"
        this.type = "TYPE"
        this.tripName = "TRIPNAME"
        this.description = "DESCRIPTION"
        this.price = "PRICE"
        this.address = "ADDRESS"
        this.city = "CITY"
        this.latitude = "LATITUDE"
        this.longitude = "LONGITUDE"
        this.phone_no = "PHONE_NO"
        this.reviews = "REVIEWS"
        this.website = "WEBSITE"
        this.diet = "DIET"
        this.no_of_people_reviwed="NO_OF_PEOPLE_REVIEWED"
        this.dropTable="drop table if exists $table_name"
        this.createTable="create table $table_name(ID INTEGER PRIMARY KEY AUTOINCREMENT,TYPE TEXT,TRIPNAME TEXT,DESCRIPTION TEXT,PRICE TEXT,ADDRESS TEXT,CITY TEXT,LATITUDE TEXT,LONGITUDE TEXT,PHONE_NO TEXT,REVIEWS TEXT,WEBSITE TEXT,DIET TEXT,NO_OF_PEOPLE_REVIEWED TEXT)"

    }
    fun getTripDetails(context:Context) : MutableList<TripDetails>
    {

        val dbHelpers : Database = Database(context,createTable,dropTable)
        val db : SQLiteDatabase = dbHelpers.readableDatabase
        val sqlSelect:Array<String> = arrayOf(id,type,tripName,description,price,address,city,latitude,longitude,phone_no,reviews,website,diet)
        val cursor : Cursor = db.rawQuery("select * from  ${table_name}",null)
        val result  = mutableListOf<TripDetails>()
        if(cursor.moveToFirst())
        {
            do{
                var tripDetails : TripDetails = TripDetails()
                tripDetails.id=cursor.getInt(cursor.getColumnIndex(id))
                tripDetails.type=cursor.getString(cursor.getColumnIndex(type))
                tripDetails.tripName=cursor.getString(cursor.getColumnIndex(tripName))
                tripDetails.description=cursor.getString(cursor.getColumnIndex(description))
                tripDetails.price=cursor.getString(cursor.getColumnIndex(price))
                tripDetails.address=cursor.getString(cursor.getColumnIndex(address))
                tripDetails.city=cursor.getString(cursor.getColumnIndex(city))
                tripDetails.latitude=cursor.getString(cursor.getColumnIndex(latitude))
                tripDetails.longitude=cursor.getString(cursor.getColumnIndex(longitude))
                tripDetails.phone_no=cursor.getString(cursor.getColumnIndex(phone_no))
                tripDetails.reviews=cursor.getString(cursor.getColumnIndex(reviews))
                tripDetails.website=cursor.getString(cursor.getColumnIndex(website))
                tripDetails.diet=cursor.getString(cursor.getColumnIndex(diet))
                tripDetails.no_of_people_reviewed=cursor.getString(cursor.getColumnIndex(no_of_people_reviwed))
                result.add(tripDetails)
            }while (cursor.moveToNext())
        }
        TripDetails.Supplier.tripDetails.addAll(result)
        Log.d("abcd",result.size.toString())
        cursor.close()
        db.close()
        dbHelpers.close()
        return result
    }


}