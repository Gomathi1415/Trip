package com.example.trip.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.trip.DisplayFullImageListener
import com.example.trip.R
import com.example.trip.models.Images
import kotlinx.android.synthetic.main.display_description_fragment.*

class DescriptionViewPagerAdapter(var context: Context, var images: MutableList<Images>,var listener: DisplayFullImageListener,var id :  Int) : PagerAdapter() {

    lateinit var inflater: LayoutInflater
    lateinit var itemView: View

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup,position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(id==1) {
             itemView = inflater.inflate(R.layout.desc_view_pager_item, null)
            val image: ImageView
            image = itemView.findViewById(R.id.FullbackImageView) as ImageView
            val data:ByteArray = images[position].images
            val bmp = BitmapFactory.decodeByteArray(data, 0, data.size)
            var drawable  : Drawable =  BitmapDrawable(Resources.getSystem(),bmp)
            image.setImageDrawable(drawable)
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(itemView, 0)

        }else
        {
            itemView = inflater.inflate(R.layout.full_screen_view_pager, null)
            val image: ImageView
            image = itemView.findViewById(R.id.FullbackImageView) as ImageView
            val data:ByteArray = images[position].images
            val bmp = BitmapFactory.decodeByteArray(data, 0, data.size)
            var drawable  : Drawable =  BitmapDrawable(Resources.getSystem(),bmp)
            image.setImageDrawable(drawable)
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(itemView, 0)

        }

        itemView.setOnClickListener {
          listener.openImage(position.toString(),"")

        }

        return itemView
    }


    override fun destroyItem(container: ViewGroup, position: Int, object1: Any) {
        val viewPager = container
        val view = object1 as View
        viewPager.removeView(view)
    }
}