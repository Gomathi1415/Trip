package com.example.trip.DAO

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.trip.models.Images
import database.Database

class ImageDAO {

    var id: String
    var image :String

    var table_name :String

    var dropTable  :String
    var createTable  :String


    init {
        this.id = "TRIP_ID"
        this.table_name = "images"
        this.image = "IMAGE"
        this.dropTable="drop table if exists $table_name"
        this.createTable="create table $table_name($id INTEGER,$image BLOB)"

    }
    fun getImage(context: Context) : MutableList<Images>
    {

        val dbHelpers : Database = Database(context,createTable,dropTable)
        val db : SQLiteDatabase = dbHelpers.readableDatabase
        val cursor : Cursor = db.rawQuery("select * from  ${table_name}",null)
        val result  = mutableListOf<Images>()
        if(cursor.moveToFirst())
        {
            do{
                var images : Images = Images()
                images.id=cursor.getInt(cursor.getColumnIndex(this.id))
                images.images=cursor.getBlob(cursor.getColumnIndex(image))

                result.add(images)
            }while (cursor.moveToNext())
        }
        Images.Supplier.images.clear()
        Images.Supplier.images.addAll(result)
        cursor.close()
        db.close()
        dbHelpers.close()
        return result
    }
    fun getTripImage(id : Int)
    {
        val result  = mutableListOf<Images>()
       for(index in Images.Supplier.images)
       {
           if(index.id==id)
               result.add(index)

       }
        Images.Supplier.tripImage.clear()
        Images.Supplier.tripImage.addAll(result)
    }


}
