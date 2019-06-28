package com.example.trip.models

class Images
{

    var id: Int =0
    lateinit var images :ByteArray



    constructor(
        id: Int,
        images: ByteArray


    ) {
        this.id = id
        this.images = images


    }

    constructor() {

    }


    object Supplier {
        val images = mutableListOf<Images>()
        val tripImage = mutableListOf<Images>()
    }

}
