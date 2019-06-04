package com.example.trip.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabAdapter(fragmentManager:FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    var fragments=mutableListOf<Fragment>()
    var tabTitles= mutableListOf<String>()

    fun addFragment(fragment:Fragment,tabTitle:String){
        fragments.add(fragment)
        tabTitles.add(tabTitle)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles.get(position)
    }

    override fun getItem(p0: Int): Fragment {
        return fragments.get(p0)
    }

    override fun getCount(): Int {
        return fragments.size
    }
}