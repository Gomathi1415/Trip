package com.example.trip.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.trip.R

class ViewPagerAdapter(
    var context: Context,
    var images: Array<Int>,
    var mainText: Array<String>,
    var subText: Array<String>
) : PagerAdapter() {

    lateinit var inflater: LayoutInflater
    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = inflater.inflate(R.layout.view_pager_item, null)
        val image: LinearLayout
        val backtext: TextView
        val backSubText: TextView
        backSubText = itemView.findViewById(R.id.backgroundSubText) as TextView
        backtext = itemView.findViewById(R.id.backgroundMainText) as TextView
        image = itemView.findViewById(R.id.backImageView) as LinearLayout
        image.setBackgroundResource(images[position])
        backtext.setText(mainText[position])
        backSubText.setText(subText[position])
        container.addView(itemView, 0)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, object1: Any) {
        val viewPager = container
        val view = object1 as View
        viewPager.removeView(view)
    }
}