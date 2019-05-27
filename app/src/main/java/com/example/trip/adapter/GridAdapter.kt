package com.example.trip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.trip.R


class GridAdapter(private val mContext: Context, private val userPreference: Array<String>, private val userPreferenceImage: IntArray) :
BaseAdapter() {

    override fun getCount(): Int {
        return userPreference.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var grid: View
        val inflater = mContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {

            grid = View(mContext)
            grid = inflater.inflate(R.layout.grid_card_view, null)
            val textView = grid.findViewById(R.id.grid_text) as TextView
            val imageView = grid.findViewById(R.id.grid_image) as ImageView
            textView.text = userPreference[position]
            imageView.setImageResource(userPreferenceImage[position])
        } else {
            grid = convertView
        }

        return grid
    }
}